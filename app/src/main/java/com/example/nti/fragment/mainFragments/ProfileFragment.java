package com.example.nti.fragment.mainFragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nti.R;


public class ProfileFragment extends Fragment {

    private String email;
    private TextView txtEmail;

    public ProfileFragment(String email) {
        // Required empty public constructor
        this.email = email;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        txtEmail = view.findViewById(R.id.tv_profile_email);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtEmail.setText(email);
    }
}
