package com.kv.retrorequestlib.helper;
/**
 * updated on 1/4/19.
 */
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RetroDataService {

    @GET("{path}/{path1}/{path2}")
    Call<String> dataRequestGet(@Path("path") String path, @Path("path1") String path1, @Path("path2") String path2, @QueryMap(encoded = true) Map<String, String> query);

    @DELETE("{path}/{path1}/{path2}")
    Call<String> dataRequestDelete(@Path("path") String path, @Path("path1") String path1, @Path("path2") String path2, @QueryMap(encoded = true) Map<String, String> query);

    @FormUrlEncoded
    @POST("{path}/{path1}/{path2}")
    Call<String> dataRequestPost(@Path("path") String path, @Path("path1") String path1, @Path("path2") String path2, @FieldMap(encoded = true) Map<String, String> body);

    @FormUrlEncoded
    @PUT("{path}/{path1}/{path2}")
    Call<String> dataRequestPut(@Path("path") String path, @Path("path1") String path1, @Path("path2") String path2, @FieldMap(encoded = true) Map<String, String> body);

    @Multipart
    @POST("{path}/{path1}/{path2}")
    Call<String> dataRequestPostMultiPart(@Path("path") String path, @Path("path1") String path1, @Path("path2") String path2, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);

    @Multipart
    @PUT("{path}/{path1}/{path2}")
    Call<String> dataRequestPutMultiPart(@Path("path") String path, @Path("path1") String path1, @Path("path2") String path2, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);
}