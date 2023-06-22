package com.example.audioquranapi;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audioquranapi.model.fileaudio;

import java.io.IOException;
import java.util.List;

public class audioadapter extends RecyclerView.Adapter<audioadapter.ViewHolder> {

    private List<fileaudio> audioList;
    private MediaPlayer mediaPlayer;
    public audioadapter(List<fileaudio> audioList){
        this.audioList = audioList;
        mediaPlayer = new MediaPlayer();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail_surah, parent, false)
        );
    }
    @Override
    public void onBindViewHolder(@NonNull audioadapter.ViewHolder holder, int position) {

        holder.audiobutton.setOnClickListener(view -> {
            fileaudio audio = audioList.get(position);

            if (mediaPlayer.isPlaying()){
                pauseAudio();
            } else {
                playAudio(audio.getAudioUrl());
            }
        } );
    }
    private void playAudio(String Audio){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(Audio);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void pauseAudio(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    @Override
    public int getItemCount() {
        return audioList.size();
    }

    public void setData(List<fileaudio> result) {
        audioList.clear();
        audioList.addAll(result);
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button audiobutton;
        public ViewHolder(View itemView) {
            super(itemView);

            audiobutton = itemView.findViewById(R.id.audio2);
        }

    }
}
