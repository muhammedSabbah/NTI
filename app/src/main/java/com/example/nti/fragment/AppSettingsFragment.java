package com.example.nti.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nti.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppSettingsFragment extends Fragment {

    private ImageButton imageButtonFace;
    private ImageButton imageButtonTwitter;
    private ImageButton imageButtonInsta;

    public AppSettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_settings, container, false);
        imageButtonFace = view.findViewById(R.id.btn_facebook);
        imageButtonTwitter = view.findViewById(R.id.btn_twitter);
        imageButtonInsta = view.findViewById(R.id.btn_insta);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageButtonFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewApp("http://www.facebook.com");
            }
        });
        imageButtonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewApp("https://twitter.com");
            }
        });
        imageButtonInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewApp("https://instagram.com");
            }
        });
    }

    private void goToNewApp(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
