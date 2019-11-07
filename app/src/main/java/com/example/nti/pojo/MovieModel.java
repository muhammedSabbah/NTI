package com.example.nti.pojo;

public class MovieModel {
    private String movieName;
    private String movieRelease;

    private String imageUrl;

    public MovieModel(){}
    public MovieModel(String movieName, String movieRelease, String imageUrl) {
        this.movieName = movieName;
        this.movieRelease = movieRelease;
        this.imageUrl = imageUrl;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieRelease() {
        return movieRelease;
    }

    public void setMovieRelease(String movieRelease) {
        this.movieRelease = movieRelease;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
