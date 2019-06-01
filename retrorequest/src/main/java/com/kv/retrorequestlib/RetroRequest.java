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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.kv.retrorequestlib.helper.DataModel;
import com.kv.retrorequestlib.helper.RequestBodyUtils;
import com.kv.retrorequestlib.helper.ResponseDelegate;
import com.kv.retrorequestlib.helper.RetroDataService;
import com.kv.retrorequestlib.helper.ServiceGenerator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kv.retrorequestlib.helper.Utils.isOnline;
import static com.kv.retrorequestlib.helper.Utils.loge;
import static com.kv.retrorequestlib.helper.Utils.logw;
import static com.kv.retrorequestlib.helper.Utils.showToast;

public class RetroRequest extends DataModel {

    // Webservice request method types
    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    public static final String REQUEST_METHOD_PUT = "PUT";
    public static final String REQUEST_METHOD_DELETE = "DELETE";

    private Context context;
    private Dialog progressdialog;

    public RetroRequest(Context context, ResponseDelegate delegate) {
        super(delegate);
        this.context = context;
    }

    public void execute(boolean progressbar) {
        if (validater()) {
            if (progressbar) {
                showProgressBar();
            }
            try {
                Call<String> call = null;

                RetroDataService retroDataService = ServiceGenerator.createService(getBaseUrl());

                if (getFile() == null) {
                    switch (getRequestMethod()) {
                        case REQUEST_METHOD_GET:
                            call = retroDataService.dataRequestGet(getPath1(), getPath2(), getPath3(), getQuery());
                            break;
                        case REQUEST_METHOD_DELETE:
                            call = retroDataService.dataRequestDelete(getPath1(), getPath2(), getPath3(), getQuery());
                            break;
                        case REQUEST_METHOD_POST:
                            call = retroDataService.dataRequestPost(getPath1(), getPath2(), getPath3(), getQuery());
                            break;
                        case REQUEST_METHOD_PUT:
                            call = retroDataService.dataRequestPut(getPath1(), getPath2(), getPath3(), getQuery());
                            break;
                    }
                } else {
                    switch (getRequestMethod()) {
                        case REQUEST_METHOD_POST:
                            call = retroDataService.dataRequestPostMultiPart(getPath1(), getPath2(), getPath3(), getBody(), getFile());
                            break;
                        case REQUEST_METHOD_PUT:
                            call = retroDataService.dataRequestPutMultiPart(getPath1(), getPath2(), getPath3(), getBody(), getFile());
                            break;
                    }
                }
                showLog(call);

                assert call != null;
                call.enqueue(stringCallback);
            } catch (Exception e) {
                hideProgressBar();
                e.printStackTrace();
            }
        }
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
        public void onResponse(Call<String> s, Response<String> response) {
            onSuccessResponse(response);
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            hideProgressBar();
            onFailureResponse(0, t.getClass().getName() + " " + t.getMessage());
        }
    };

    private void onSuccessResponse(Response<String> response) {
        hideProgressBar();

        try {
            if (response.code() == 200 && getResponseDelegate() != null) {
                logw("Response:- " + response.body());
                getResponseDelegate().onSuccess(getTag(), response.body());
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
        if (getQuery() != null) {
            logw("QUERY:- " + getQuery());
        }
        if (getFile() != null) {
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
