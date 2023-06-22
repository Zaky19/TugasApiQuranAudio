package com.example.audioquranapi.retrofit;

import com.example.audioquranapi.model.Chapters;
import com.example.audioquranapi.model.audio;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();
    @GET("chapter_recitations/33?")
    Call<audio> getAudio();

}
