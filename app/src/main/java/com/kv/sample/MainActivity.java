package com.kv.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kv.retrorequestlib.RetroRequest;
import com.kv.retrorequestlib.helper.ResponseDelegate;
import com.kv.retrorequestlib.helper.Utils;

import static com.kv.sample.Config.getBaseUrl;
import static com.kv.sample.ResponseType.PINCODE;

public class MainActivity extends AppCompatActivity implements ResponseDelegate {

    Context context;
    TextView tv_result;
    EditText editTextpin;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        tv_result = findViewById(R.id.tv_result);
        editTextpin = findViewById(R.id.et_pincode);
    }

    //demo url = URL:- (GET) http://postalpincode.in/api/pincode/110001?user_id=1234
    private void serverRequestForGetCity(String pin) {
        RetroRequest request = new RetroRequest(context, this);
        request.setBaseUrl(getBaseUrl());   //set base_url of api example- "https://github.com/",  "https://github.com:1030/"
        request.setPath1("api");            //set path
        request.setPath2("pincode");
        request.setPath3(pin);
//        request.setPath4("master");                                      // optional if required
//        Model model = new Model();
//        model.breakInFlag = "All";
//
//        request.setObject(model);
        request.putQuery("user_id", "1234");    //for put query and body for send data use as key value pair -> &user_id=1234
        request.addHeader("Authorization", "JWT eyJhb");    // optional if required
        request.setRequestMethod(RetroRequest.GET);   //set the type of api {GET, POST, DELETE, PUT} Default GET
        request.setTag(PINCODE);    //for use multiple request in same activity  set unique tag
        request.setShowRetrySnack(true);        //for show Retry Snackbar on No Internet Connection.                Default false
        request.setShowToast(true);             //for show Toast on Failure, No Internet                            Default true
//        request.putFile("file1", new File(filepath));   //for send file,   only work with POST and PUT type
        request.execute(true);          //for execure the request
    }

    @Override
    public void onSuccess(int tag, String response) {
        if (tag == PINCODE) {
            PostOffice postOffice = new Gson().fromJson(response, PostOffice.class);
            if (postOffice.getStatus().equals("Success")) {
                //success
                tv_result.setText(gson.toJson(postOffice.getPostOffice()));
            } else {
                Utils.showToast(context, postOffice.getMessage());
            }
        }
    }

    @Override
    public void onNoNetwork(int tag, String message) {

    }

    @Override
    public void onFailure(int tag, int response_code, String message) {

    }

    public void findCity(View view) {
        String str = editTextpin.getText().toString();

        if (str.length() == 6) {
            serverRequestForGetCity(str);
        } else {
            Toast.makeText(getApplicationContext(), "Enter 6 digit of pin", Toast.LENGTH_SHORT).show();
        }
    }
}
