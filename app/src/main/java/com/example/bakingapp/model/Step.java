package com.example.bakingapp.model;

class Step {
    int id;
    String shortDescription;
    String discription;
    String video;

    public Step(String shortDescription, String discription, String video) {
        this.shortDescription = shortDescription;
        this.discription = discription;
        this.video = video;
    }

    public Step(int id, String shortDescription, String discription, String video) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.discription = discription;
        this.video = video;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", shortDescription='" + shortDescription + '\'' +
                ", discription='" + discription + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}
