package com.example.application1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import com.example.application1.R;
import com.example.application1.adapters.RecyclerviewAdapter;
import com.example.application1.pojo.GithubUserModel;
import com.example.application1.webService.ApiService;
import com.example.application1.webService.RetrofitService;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<GithubUserModel> githubUserModelList;
    private RecyclerviewAdapter recyclerviewAdapter;
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        githubUserModelList = new LinkedList<>();

        recyclerviewAdapter = new RecyclerviewAdapter(this, githubUserModelList);
        recyclerView.setAdapter(recyclerviewAdapter);

        apiService = RetrofitService.getRetrofit().create(ApiService.class);

        Call<List<GithubUserModel>> call = apiService.getUsers();
        call.enqueue(new Callback<List<GithubUserModel>>() {
            @Override
            public void onResponse(Call<List<GithubUserModel>> call, Response<List<GithubUserModel>> response) {
                githubUserModelList.addAll(response.body());
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GithubUserModel>> call, Throwable t) {

            }
        });

    }
}
