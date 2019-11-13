package com.example.nti.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.nti.R;

public class AnimationActivity extends AppCompatActivity {

    ImageView background;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        background = findViewById(R.id.iv_background);
        animation = AnimationUtils.loadAnimation(this, R.anim.startanim);

        background.animate().translationY(-1000).setDuration(800).setStartDelay(800);

    }
}
