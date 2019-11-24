package com.example.nti.pojo;

public class UserModel {
    private String name;
    private String email;
    private String password;
    private String favouriteMovie;

    public UserModel() {}
    public UserModel(String name, String email, String password,
                     String favouriteMovie) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.favouriteMovie = favouriteMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFavouriteMovie() {
        return favouriteMovie;
    }

    public void setFavouriteMovie(String favouriteMovie) {
        this.favouriteMovie = favouriteMovie;
    }
}
