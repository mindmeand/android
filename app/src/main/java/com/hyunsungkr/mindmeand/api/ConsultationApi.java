package com.hyunsungkr.mindmeand.api;

import com.hyunsungkr.mindmeand.model.Consultation;
import com.hyunsungkr.mindmeand.model.Res;
import com.hyunsungkr.mindmeand.model.UserHistory;
import com.hyunsungkr.mindmeand.model.UserHistoryList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConsultationApi {

    // 상담하기
    @POST("/consultation")
    Call<Consultation> consultation(@Header("Authorization") String token,
                           @Body Consultation consultation);

    // 내 상담내역 가져오기.
    @GET("/consultation/my")
    Call<UserHistoryList> getMyConsultation(@Header("Authorization") String token,@Query("offset") int offset,
                                            @Query("limit") int limit);

    // 상담내역 삭제
    @DELETE("/consultation/{consultationId}")
    Call<Res> deleteConsultation(@Header("Authorization") String token,
                                 @Path("consultationId") int consultationId);

}