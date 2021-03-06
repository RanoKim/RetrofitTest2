package com.ticcorp.retrofittest2.retrofit2Interface;

import com.ticcorp.retrofittest2.DTO.FriendsScoreView;
import com.ticcorp.retrofittest2.DTO.MyScoreDTO;
import com.ticcorp.retrofittest2.DTO.ScoreView;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Daesub Kim on 2016-08-15.
 */
public interface MyScoreInterface {

    @FormUrlEncoded
    @POST("myscore.do")
    Call<MyScoreDTO> getMyScore(
            @Field("service") String service,
            @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("myscore.do")
    Call<MyScoreDTO> insertMyScore(
            @Field("service") String service,
            @Field("userId") String userId,
            @Field("exp") int exp,
            @Field("userLevel") int userLevel
    );

    @FormUrlEncoded
    @POST("myscore.do")
    Call<MyScoreDTO> updateMyScore(
            @Field("service") String service,
            @Field("userId") String userId,
            @Field("exp") int exp,
            @Field("userLevel") int userLevel
    );

    @FormUrlEncoded
    @POST("myscore.do")
    Call<MyScoreDTO> retrieveMyScore(
            @Field("service") String service,
            @Field("userId") String userId
    );

    @FormUrlEncoded
    @POST("myscore.do")
    Call<List<ScoreView>> retrieveScores(
            @Field("service") String service,
            @Field("userId") String userId
    );


    @FormUrlEncoded
    @POST("myscore.do")
    Call<List<FriendsScoreView>> retrieveFriendsScores(
            @Field("service") String service,
            @Field("userId") String userId,
            @Field("friends") String friends
    );
}

