package com.example.myapplication.models;

import java.io.Serializable;

public class Lesson implements Serializable {
    private String id;
    private String title;
    private String duration;
    private String videoUrl;
    private String content;

    public Lesson() {
    }

    public Lesson(String id, String title, String duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
