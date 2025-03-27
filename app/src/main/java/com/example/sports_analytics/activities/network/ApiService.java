package com.example.sports_analytics.activities.network;

import com.example.sports_analytics.activities.models.VideoAnalysisResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @Multipart
    @POST("analyze")
    Call<VideoAnalysisResponse> uploadVideo(
            @Part MultipartBody.Part video,
            @Query("confidence") double confidence
    );
}
