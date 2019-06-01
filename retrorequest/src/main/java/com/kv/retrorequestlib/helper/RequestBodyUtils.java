/**
 * updated on 1/5/19.
 */
package com.kv.retrorequestlib.helper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RequestBodyUtils {

    public static MultipartBody.Part getRequestBodyImage(String key, File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(key, file.getName(), requestFile);
    }

    public static RequestBody getRequestBodyString(String text) {
        return RequestBody.create(MediaType.parse("text/plain"), text);
    }

}
