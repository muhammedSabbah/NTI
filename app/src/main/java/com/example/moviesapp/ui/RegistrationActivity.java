package com.example.moviesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.moviesapp.R;
import com.example.moviesapp.fragment.Login;

public class RegistrationActivity extends AppCompatActivity {

    private Login loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        attachFragments();

    }

    private void attachFragments() {
        loginFragment = new Login();

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fr_layout, loginFragment);
        transaction.commit();
    }
}
