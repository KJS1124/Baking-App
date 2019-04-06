package com.example.bakingapp.model;

import java.io.Serializable;

public class Step implements Serializable {
    int id;
    String shortDescription;
    String description;
    String videoURL;

    public Step(String shortDescription, String description, String videoURL) {
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
    }

    public Step(int id, String shortDescription, String description, String videoURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", description='" + description + '\'' +
                ", videoURL='" + videoURL + '\'' +
                '}';
    }
}
