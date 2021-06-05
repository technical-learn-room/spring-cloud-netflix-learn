package com.me.service_one;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceTwoConnection {

    @Headers(value = {"accept: application/json", "content-type: application/json"})
    @POST("/test")
    Call<TestResponse> connectServiceTwo(@Body TestRequest request);
}
