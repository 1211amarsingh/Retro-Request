package com.kv.retrorequestlib.helper;
/**
 * updated on 1/4/19.
 */

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
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

public interface ApiServiceInterface {

    // two path----------------------------------------------------------------------------------------------
    @GET("{path1}/{path2}")
    Call<String> get(@Path("path1") String path1, @Path("path2") String path2, @QueryMap(encoded = true) Map<String, String> query);

    @DELETE("{path1}/{path2}")
    Call<String> delete(@Path("path1") String path1, @Path("path2") String path2, @QueryMap(encoded = true) Map<String, String> query);

    @FormUrlEncoded
    @POST("{path1}/{path2}")
    Call<String> post(@Path("path1") String path1, @Path("path2") String path2, @FieldMap(encoded = true) Map<String, String> body);

    @FormUrlEncoded
    @PUT("{path1}/{path2}")
    Call<String> put(@Path("path1") String path1, @Path("path2") String path2, @FieldMap(encoded = true) Map<String, String> body);

    @Multipart
    @POST("{path1}/{path2}")
    Call<String> postMultiPart(@Path("path1") String path1, @Path("path2") String path2, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);

    @Multipart
    @PUT("{path1}/{path2}")
    Call<String> putMultiPart(@Path("path1") String path1, @Path("path2") String path2, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);

    // three path----------------------------------------------------------------------------------------------
    @GET("{path1}/{path2}/{path3}")
    Call<String> get(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @QueryMap(encoded = true) Map<String, String> query);

    @DELETE("{path1}/{path2}/{path3}")
    Call<String> delete(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @QueryMap(encoded = true) Map<String, String> query);

    @FormUrlEncoded
    @POST("{path1}/{path2}/{path3}")
    Call<String> post(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @FieldMap Map<String, String> body);//(encoded = true)

    @FormUrlEncoded
    @PUT("{path1}/{path2}/{path3}")
    Call<String> put(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @FieldMap(encoded = true) Map<String, String> body);

    @Multipart
    @POST("{path1}/{path2}/{path3}")
    Call<String> postMultiPart(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);

    @Multipart
    @PUT("{path1}/{path2}/{path3}")
    Call<String> putMultiPart(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);

// four path----------------------------------------------------------------------------------------------

    @GET("{path1}/{path2}/{path3}/{path4}")
    Call<String> get(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @QueryMap(encoded = true) Map<String, String> query);

    @DELETE("{path1}/{path2}/{path3}/{path4}")
    Call<String> delete(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @QueryMap(encoded = true) Map<String, String> query);

    @FormUrlEncoded
    @POST("{path1}/{path2}/{path3}/{path4}")
    Call<String> post(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @FieldMap(encoded = true) Map<String, String> body);

    @POST("{path1}/{path2}/{path3}/{path4}")
    Call<String> postBody(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4,
                              @Body Object object);

    @FormUrlEncoded
    @PUT("{path1}/{path2}/{path3}/{path4}")
    Call<String> put(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @FieldMap(encoded = true) Map<String, String> body);

    @Multipart
    @POST("{path1}/{path2}/{path3}/{path4}")
    Call<String> postMultiPart(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);

    @Multipart
    @PUT("{path1}/{path2}/{path3}/{path4}")
    Call<String> putMultiPart(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);

    // five path----------------------------------------------------------------------------------------------

    @GET("{path1}/{path2}/{path3}/{path4}/{path5}")
    Call<String> get(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @Path("path5") String path5, @QueryMap(encoded = true) Map<String, String> query);

    @DELETE("{path1}/{path2}/{path3}/{path4}/{path5}")
    Call<String> delete(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @Path("path5") String path5, @QueryMap(encoded = true) Map<String, String> query);

    @FormUrlEncoded
    @POST("{path1}/{path2}/{path3}/{path4}/{path5}")
    Call<String> post(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @Path("path5") String path5, @FieldMap(encoded = true) Map<String, String> body);

    @FormUrlEncoded
    @PUT("{path1}/{path2}/{path3}/{path4}/{path5}")
    Call<String> put(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @Path("path5") String path5, @FieldMap(encoded = true) Map<String, String> body);

    @Multipart
    @POST("{path1}/{path2}/{path3}/{path4}/{path5}")
    Call<String> postMultiPart(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @Path("path5") String path5, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);

    @Multipart
    @PUT("{path1}/{path2}/{path3}/{path4}/{path5}")
    Call<String> putMultiPart(@Path("path1") String path1, @Path("path2") String path2, @Path("path3") String path3, @Path("path4") String path4, @Path("path5") String path5, @PartMap Map<String, RequestBody> body, @Part List<MultipartBody.Part> files);
}