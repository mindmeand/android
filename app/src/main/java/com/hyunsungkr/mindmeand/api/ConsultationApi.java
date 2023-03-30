package com.hyunsungkr.mindmeand.api;

import com.hyunsungkr.mindmeand.model.Consultation;
import com.hyunsungkr.mindmeand.model.Res;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ConsultationApi {

    // 상담하기
    @POST("/consultation")
    Call<Consultation> consultation(@Header("Authorization") String token,
                           @Body Consultation consultation);

}
