package com.example.application1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.application1.R;
import com.example.application1.adapters.RecyclerviewAdapter;
import com.example.application1.pojo.GithubUserModel;
import com.example.application1.webService.ApiService;
import com.example.application1.webService.RetrofitService;
import com.google.android.material.navigation.NavigationView;

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

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.item_home:
                        Toast.makeText(HomeActivity.this, " home page ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_help:
                        Toast.makeText(HomeActivity.this, " help page ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item_sign_out:
                        Toast.makeText(HomeActivity.this, " Sign out  ", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
}
