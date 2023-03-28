package com.hyunsungkr.mindmeand.api;

import com.hyunsungkr.mindmeand.model.User;
import com.hyunsungkr.mindmeand.model.UserRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {



    @POST("/user/register")
    Call<UserRes> register(@Body User user);
}
