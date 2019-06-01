/**
 * updated on 1/5/19.
 */
package com.kv.retrorequestlib.helper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;


public class ApiClient {

    public static <S> S create(String baseUrl) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(10, TimeUnit.SECONDS);
        httpClient.writeTimeout(10, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);

        httpClient.interceptors().add(chain -> {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
//            builder.header("Authorization", getJwtToken(context));
            Request request = builder.method(original.method(), original.body()).build();
            return chain.proceed(request);
        });

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl(baseUrl)
                .build();
        return retrofit.create((Class<S>) ApiServiceInterface.class);
    }
}
