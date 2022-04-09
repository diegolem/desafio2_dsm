package com.example.desafio2dsm_lt171997_au171965.model;

public class Movie {
    private String title;
    private String releaseyear;
    private String rating;
    private String description;

    private String id;
    public Movie (){ }

    public Movie (String title, String description, String releaseyear, String rating){
        this.title = title;
        this.description = description;
        this.releaseyear = releaseyear;
        this.rating = rating;
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

    public String getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(String releaseyear) {
        this.releaseyear = releaseyear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
