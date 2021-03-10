/**
 * updated on 1/5/19.
 */
package com.kv.retrorequestlib;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.kv.retrorequestlib.helper.ApiClient;
import com.kv.retrorequestlib.helper.ApiServiceInterface;
import com.kv.retrorequestlib.helper.DataModel;
import com.kv.retrorequestlib.helper.RequestBodyUtils;
import com.kv.retrorequestlib.helper.ResponseDelegate;

import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kv.retrorequestlib.helper.Utils.getBuildConfigValue;
import static com.kv.retrorequestlib.helper.Utils.isOnline;
import static com.kv.retrorequestlib.helper.Utils.loge;
import static com.kv.retrorequestlib.helper.Utils.logw;
import static com.kv.retrorequestlib.helper.Utils.showToast;

public class RetroRequest extends DataModel {

    // Webservice request method types
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    private Context context;
    private Dialog progressdialog;

    public RetroRequest(Context context, ResponseDelegate delegate) {
        super(delegate);
        this.context = context;
        setShowLog((Boolean) getBuildConfigValue(context, "DEBUG"));
        Log.w("DEBUG MODE", isShowLog() + "");
    }

    public void execute(boolean progressbar) {
        if (validater()) {
            if (progressbar) {
                showProgressBar();
            }
            try {
                Call<String> call;

                ApiServiceInterface apiServiceInterface = ApiClient.create(getBaseUrl(), getHeader());

                if (getPath4() != null && !getPath4().equals("")) {
                    call = getMethodFor4Part(apiServiceInterface);
                } else {
                    call = getMethodFor3Part(apiServiceInterface);
                }
                showLog(call);

                call.enqueue(stringCallback);
            } catch (Exception e) {
                hideProgressBar();
                e.printStackTrace();
            }
        }
    }

    private Call<String> getMethodFor4Part(ApiServiceInterface apiServiceInterface) {
        Call<String> call = null;

        switch (getRequestMethod()) {
            case GET:
                call = apiServiceInterface.get(getPath1(), getPath2(), getPath3(), getPath4(), getQuery());
                break;
            case DELETE:
                call = apiServiceInterface.delete(getPath1(), getPath2(), getPath3(), getPath4(), getQuery());
                break;
            case POST:
                if (getFile() == null && getObject() == null) {
                    call = apiServiceInterface.post(getPath1(), getPath2(), getPath3(), getPath4(), getQuery());
                } else if (getObject() != null) {
                    call = apiServiceInterface.postBody(getPath1(), getPath2(), getPath3(), getPath4(), getObject());
                } else {
                    call = apiServiceInterface.postMultiPart(getPath1(), getPath2(), getPath3(), getPath4(), getBody(), getFile());
                }
                break;
            case PUT:
                if (getFile() == null) {
                    call = apiServiceInterface.put(getPath1(), getPath2(), getPath3(), getPath4(), getQuery());
                } else {
                    call = apiServiceInterface.putMultiPart(getPath1(), getPath2(), getPath3(), getPath4(), getBody(), getFile());
                }
                break;
        }
        return call;
    }

    private Call<String> getMethodFor3Part(ApiServiceInterface apiServiceInterface) {
        Call<String> call = null;

        switch (getRequestMethod()) {
            case GET:
                call = apiServiceInterface.get(getPath1(), getPath2(), getPath3(), getQuery());
                break;
            case DELETE:
                call = apiServiceInterface.delete(getPath1(), getPath2(), getPath3(), getQuery());
                break;
            case POST:
                if (getFile() == null) {
                    call = apiServiceInterface.post(getPath1(), getPath2(), getPath3(), getQuery());
                } else {
                    call = apiServiceInterface.postMultiPart(getPath1(), getPath2(), getPath3(), getBody(), getFile());
                }
                break;
            case PUT:
                if (getFile() == null) {
                    call = apiServiceInterface.put(getPath1(), getPath2(), getPath3(), getQuery());
                } else {
                    call = apiServiceInterface.putMultiPart(getPath1(), getPath2(), getPath3(), getBody(), getFile());
                }
                break;
        }
        return call;
    }

    private boolean validater() {
        if (context == null) {
            loge("context is null");
            return false;
        }

        if (!isOnline(context)) {
            if (getResponseDelegate() != null) {
                getResponseDelegate().onNoNetwork(getTag(), context.getString(R.string.check_your_internet));
            }
            if (isShowToast() && !isShowRetrySnack()) {
                showToast(context, context.getString(R.string.check_your_internet));
            }
            if (isShowRetrySnack()) {
                showSnack(context);
            }
            return false;
        }

        if (getBaseUrl() == null || getBaseUrl().equals("")) {
            loge("base url missing");
            return false;
        }

        try {
            new URL(getBaseUrl()).toURI();
        } catch (MalformedURLException e) {
            loge("java.net.MalformedURLException: Protocol not found: " + getBaseUrl() + "\n" + "Add http:// before the www... or https://");
            return false;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private Callback<String> stringCallback = new Callback<String>() {
        @Override
        public void onResponse(@NotNull Call<String> s, @NotNull Response<String> response) {
            onSuccessResponse(response);
        }

        @Override
        public void onFailure(@NotNull Call<String> call, Throwable t) {
            hideProgressBar();
            onFailureResponse(0, t.getClass().getName() + " " + t.getMessage());
        }
    };

    private void onSuccessResponse(Response<String> response) {
        hideProgressBar();

        try {
            if (response.code() >= 200 && response.code() <= 299 && getResponseDelegate() != null) {
                logw("Response:- " + response.body());
                getResponseDelegate().onSuccess(getTag(), response.code(), response.body());
            } else {
                onFailureResponse(response.code(), response.message() + "\n" + response.errorBody().string());
            }
        } catch (Exception e) {
            onFailureResponse(response.code(), e.getClass().getName());
        }
    }

    private void onFailureResponse(int code, String message) {
        loge("onFailure :- Status Code : " + code + " Message : " + message);

        if (getResponseDelegate() != null) {
            getResponseDelegate().onFailure(getTag(), code, message);
        }
        if (isShowToast()) {
            showToast(context, "Something went wrong");
        }
    }

    private HashMap<String, RequestBody> getBody() {
        HashMap<String, RequestBody> bodyHashMap = new HashMap<>();

        for (Map.Entry<String, String> query : getQuery().entrySet()) {
            bodyHashMap.put(query.getKey(), RequestBodyUtils.getRequestBodyString(query.getValue()));
        }
        return bodyHashMap;
    }

    private void showLog(Call<String> call) {
        if (getQuery() != null && !getRequestMethod().equals(GET) && !getRequestMethod().equals(DELETE)) {
            logw("QUERY:- " + getQuery());
        }
        if (getFile() != null && !getRequestMethod().equals(GET) && !getRequestMethod().equals(DELETE)) {
            logw("FILES" + getFile());
        }
        logw("URL:- (" + getRequestMethod() + ") " + call.request().url().toString());
    }

    private void showProgressBar() {
        try {
            progressdialog = new Dialog(context);
            progressdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressdialog.setContentView(R.layout.progress_bar);
            progressdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressdialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            progressdialog.setCancelable(false);
            progressdialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideProgressBar() {
        if (progressdialog != null && progressdialog.isShowing()) {
            progressdialog.dismiss();
        }
    }

    private void showSnack(Context context) {
        try {
            View parentLayout = ((Activity) context).findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "No Connection", Snackbar.LENGTH_LONG).setAction("Retry", v -> execute(true)).show();
        } catch (Exception ignore) {

        }
    }
}
