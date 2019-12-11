package com.example.nti.fragment.settingsFragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nti.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RatingSettingsFragment extends Fragment {


    private RatingBar ratingBar;
    private ImageView imageView;
    private TextView txtRate;
    private String answerValue;

    private Animation animation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating_settings, container, false);
        ratingBar = view.findViewById(R.id.rate);
        imageView = view.findViewById(R.id.img_rate);
        txtRate = view.findViewById(R.id.tv_rate);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.rateimg);
        imageView.startAnimation(animation);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                answerValue = String.valueOf( (int) (ratingBar.getRating()) );
                if(answerValue.equals("1")){
                    imageView.setImageResource(R.drawable.ic_onestar);
                    imageView.startAnimation(animation);
                    txtRate.setText("Just So So");
                }
                else if(answerValue.equals("2")){
                    imageView.setImageResource(R.drawable.ic_twostar);
                    imageView.startAnimation(animation);
                    txtRate.setText("Just So So");
                }
                else if(answerValue.equals("3")){
                    imageView.setImageResource(R.drawable.ic_threestar);
                    imageView.startAnimation(animation);
                    txtRate.setText("Good");
                }
                else if(answerValue.equals("4")){
                    imageView.setImageResource(R.drawable.ic_fourstar);
                    imageView.startAnimation(animation);
                    txtRate.setText("Good Job");
                }
                else{
                    imageView.setImageResource(R.drawable.ic_fivestar);
                    imageView.startAnimation(animation);
                    txtRate.setText("Awesome");
                }
            }
        });
    }
}
