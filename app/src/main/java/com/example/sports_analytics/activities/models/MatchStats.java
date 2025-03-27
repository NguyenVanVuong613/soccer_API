package com.example.sports_analytics.activities.models;

import com.google.gson.annotations.SerializedName;

public class MatchStats {
    @SerializedName("total_frames")
    private int totalFrames;

    @SerializedName("video_resolution")
    private String videoResolution;

    // Getter cho totalFrames
    public int getTotalFrames() {
        return totalFrames;
    }

    // Setter cho totalFrames
    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }

    // Getter cho videoResolution
    public String getVideoResolution() {
        return videoResolution;
    }

    // Setter cho videoResolution
    public void setVideoResolution(String videoResolution) {
        this.videoResolution = videoResolution;
    }
}
