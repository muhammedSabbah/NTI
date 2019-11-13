package com.example.nti.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.nti.R;
import com.example.nti.fragment.registerFragments.LoginFragment;

public class RegistrationActivity extends AppCompatActivity {

    private LoginFragment loginFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        attachFragments();
    }

    private void attachFragments() {
        loginFragment = new LoginFragment();

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fr_layout, loginFragment);
        transaction.commit();
    }
}
