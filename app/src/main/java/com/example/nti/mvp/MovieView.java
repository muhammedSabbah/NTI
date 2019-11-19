package com.example.nti.mvp;

import com.example.nti.pojo.MovieModel;

import java.util.List;

public interface MovieView {
    void onGetMovieModel(List<MovieModel> movieModelList);
}
