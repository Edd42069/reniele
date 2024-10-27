package com.example.brickbreaker;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("register.php")
    Call<Void> registerUser(
            @Field("user_name") String userName,
            @Field("user_email") String userEmail,
            @Field("user_password") String userPassword
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<Void> loginUser(
            @Field("user_email") String userEmail,
            @Field("user_password") String userPassword
    );
}
