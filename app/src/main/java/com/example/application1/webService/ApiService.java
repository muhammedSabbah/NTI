package com.example.application1.webService;

import com.example.application1.pojo.GithubUserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    public Call<List<GithubUserModel>> getUsers();
}
