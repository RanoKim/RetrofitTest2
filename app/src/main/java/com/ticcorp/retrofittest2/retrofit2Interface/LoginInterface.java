package com.ticcorp.retrofittest2.retrofit2Interface;

import com.ticcorp.retrofittest2.DTO.LoginView;
import com.ticcorp.retrofittest2.DTO.UserDTO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Daesub Kim on 2016-08-15.
 */
public interface LoginInterface {
    @FormUrlEncoded
    @POST("login.do")
    Call<UserDTO> oldLogin(
            @Field("userId") String userId,
            @Field("name") String name,
            @Field("platform") String platform
    );

    @FormUrlEncoded
    @POST("login.do")
    Call<LoginView> requestLogin(
            @Field("userId") String userId,
            @Field("name") String name,
            @Field("platform") String platform
    );
}