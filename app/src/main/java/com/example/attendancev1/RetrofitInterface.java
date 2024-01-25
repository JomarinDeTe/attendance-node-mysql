package com.example.attendancev1;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

public interface RetrofitInterface {
    @FormUrlEncoded
    @POST("login") // Replace with your actual endpoint
    Call<LoginResult> login(
            @Field("username") String username,
            @Field("password") String password
    );

}
