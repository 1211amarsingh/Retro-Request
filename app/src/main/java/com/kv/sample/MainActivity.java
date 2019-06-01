package com.kv.sample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.kv.retrorequestlib.RetroRequest;
import com.kv.retrorequestlib.helper.ResponseDelegate;
import com.kv.retrorequestlib.helper.Utils;

import static com.kv.sample.Config.getBaseUrl;

public class MainActivity extends AppCompatActivity implements ResponseDelegate {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        serverRequestForGetData();
    }
//  Example   https://github.com/1211amarsingh/swiggy/blob/master/README.md

//        request.setShowToast(false);
//        request.execute(true);                for execute the api request

    private void serverRequestForGetData() {
        RetroRequest request = new RetroRequest(context, this);
        request.setBaseUrl(getBaseUrl());   //set base_url of api example- "https://github.com/",  "https://github.com:1030/"
        request.setPath1("v1.0");      //set path
        request.setPath2("swiggy");
        request.setPath3("blob");
        request.setPath4("master");                                      // optional if required
        request.setPath5("README.md");                                   // optional if required
        request.addHeader("Authorization", "JWT eyJhb");    // optional if required
        request.setRequestMethod(RetroRequest.GET);   //set the type of api {GET, POST, DELETE, PUT} Default GET
        request.setTag(ResponseType.SIGNUP);    //for use multiple request in same activity  set unique tag
        request.setShowRetrySnack(true);        //for show Retry Snackbar on No Internet Connection.                Default false
        request.setShowToast(true);             //for show Toast on Failure, No Internet                            Default true
        request.setShowLog(BuildConfig.DEBUG);               //for show Log of api request and data
        request.putQuery("user_id", "1234");    //for put query and body for send data use as key value pair -> &user_id=1234
//        request.putFile("file1", new File(filepath));   //for send file,   only work with POST and PUT type
        request.execute(true);          //for execure the request
    }

    @Override
    public void onSuccess(int tag, String response) {
        ResponsePojo responsePojo = new Gson().fromJson(response, ResponsePojo.class);
        if (responsePojo.isStatus()) {
            //success
        } else {
            Utils.showToast(context, responsePojo.getMessage());
        }
    }

    @Override
    public void onNoNetwork(int tag, String message) {

    }

    @Override
    public void onFailure(int tag, int response_code, String message) {

    }
}
