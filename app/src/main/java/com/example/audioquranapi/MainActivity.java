package com.example.audioquranapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.audioquranapi.model.Chapters;
import com.example.audioquranapi.model.ChaptersItem;
import com.example.audioquranapi.model.fileaudio;
import com.example.audioquranapi.retrofit.ApiService;
import com.example.audioquranapi.model.audio;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;

    private RecyclerView recyclerView;

    private audioadapter audioAdapter;

    private List<fileaudio> audio = new ArrayList<>();

    private List<ChaptersItem> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();
        getDataFromApiAudio();
        setUpView();
        setUpRecyclerView();
    }

    private void getDataFromApiAudio() {ApiService.endpoint().getAudio().enqueue(new Callback<audio>() {
        @Override
        public void onResponse(Call<audio> call, Response<audio> response) {
            audioAdapter = new audioadapter(audio);
            if (response.isSuccessful()){
                assert response.body() != null;
                List<fileaudio> audio = response.body().getAudioFiles();
                audioAdapter.setData(audio);
            }
        }
        @Override
        public void onFailure(Call<audio> call, Throwable t) {

        }
    });
    }

    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(result);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getDataFromApi(){
        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if(response.isSuccessful()){
                    List<ChaptersItem> result = response.body().getChapters();
                    Log.d("Main", result.toString());
                    mainAdapter.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
        });
    }
}