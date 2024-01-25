package com.example.attendancev1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {


    @POST("/login")
    Call<LoginResult> userLogin(@Body LoginRequest loginRequest);
}