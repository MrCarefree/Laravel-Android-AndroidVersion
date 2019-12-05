package com.mrcarefree.laravel_android.data.network;

import com.mrcarefree.laravel_android.data.model.login.ResponseLogin;
import com.mrcarefree.laravel_android.data.model.register.ResponseRegister;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiEndPoint {
    @FormUrlEncoded
    @POST("register")
    Single<ResponseRegister> postRegister(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login")
    Single<ResponseLogin> postLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
