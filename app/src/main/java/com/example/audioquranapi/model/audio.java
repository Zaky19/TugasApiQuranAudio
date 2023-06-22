package com.example.audioquranapi.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class audio{

    @SerializedName("audio_files")
    private List<fileaudio> audioFiles;

    public List<fileaudio> getAudioFiles(){
        return audioFiles;
    }

    @Override
    public String toString(){
        return
                "Audio{" +
                        "audio_files = '" + audioFiles + '\'' +
                        "}";
    }
}
