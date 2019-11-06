package com.example.moviesapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviesapp.R;
import com.example.moviesapp.fragment.MoviesFragment;
import com.example.moviesapp.fragment.ProfileFragment;
import com.example.moviesapp.pojo.SessionManager;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private FragmentTransaction transaction;

    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);
        sideMenuOperations();

        attachMovieFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean isLogin = sessionManager.IS_LOGIN();
        if(!isLogin){
            Intent intent = new Intent(HomeActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    }

    private void sideMenuOperations() {


        email = sessionManager.getData().get(sessionManager.KEY_EMAIL);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_button);

        View headerView = navigationView.getHeaderView(0);
        TextView tvEmail = headerView.findViewById(R.id.tv_email);
        tvEmail.setText(email);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.action_home:
                        navigateMoviesFragment();
                        Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                        navigateProfileFragment();
                        break;
                    case R.id.action_help:
                        Toast.makeText(HomeActivity.this, "Help!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_settings:
                        Toast.makeText(HomeActivity.this, "Settings!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_signout:
                        FirebaseAuth.getInstance().signOut();
                        sessionManager.clearData();
                        startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
                        //Toast.makeText(HomeActivity.this, "Sign out!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void attachMovieFragment() {

        MoviesFragment moviesFragment = new MoviesFragment();

        transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fr_home_layout, moviesFragment);
        transaction.commit();
    }

    private void navigateProfileFragment(){
        ProfileFragment profileFragment = new ProfileFragment(email);

        transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_home_layout, profileFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void navigateMoviesFragment(){
        MoviesFragment moviesFragment = new MoviesFragment();

        transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_home_layout, moviesFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
