package com.hyunsungkr.mindmeand.api;

import com.hyunsungkr.mindmeand.model.Res;
import com.hyunsungkr.mindmeand.model.User;
import com.hyunsungkr.mindmeand.model.UserRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserApi {


    // 회원가입
    @POST("/user/register")
    Call<UserRes> register(@Body User user);

    // 로그인
    @POST("/user/login")
    Call<UserRes> login(@Body User user);

    // 정보수정
    @PUT("/user/info")
    Call<Res> infoChange(@Header("Authorization") String token, @Body User user);

    // 내 정보 조회
    @GET("/user/info")
    Call<User> getMyinfo(@Header("Authorization") String token);


}
