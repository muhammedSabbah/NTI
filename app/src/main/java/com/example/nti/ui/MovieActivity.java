package com.example.nti.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nti.R;
import com.example.nti.pojo.MovieModel;

public class MovieActivity extends AppCompatActivity {

    private MovieModel movieModel;

    private ImageView imgPoster;
    private ImageView imgLogo;
    private TextView adult;
    private TextView language;
    private TextView overView;
    private TextView popularity;
    private TextView title;
    private TextView releaseDate;
    private TextView hasVideo;
    private TextView voteCount;
    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent intent = getIntent();
        Bundle B = intent.getExtras();
        movieModel = (MovieModel) B.getSerializable("model");

        getLayoutComponents();
        setLayoutComponentsValue();
    }
    private void getLayoutComponents() {
        imgPoster = findViewById(R.id.img_movie_poster);
        imgLogo = findViewById(R.id.img_movie_logo);
        adult = findViewById(R.id.tv_adult_value);
        language = findViewById(R.id.tv_language_value);
        overView = findViewById(R.id.tv_overview_value);
        popularity = findViewById(R.id.tv_popularity_value);
        title = findViewById(R.id.tv_title_value);
        releaseDate = findViewById(R.id.tv_release_date_value);
        hasVideo = findViewById(R.id.tv_video_value);
        voteCount = findViewById(R.id.tv_vote_count_value);
        ratingBar = findViewById(R.id.rating_bar);
    }
    private void setLayoutComponentsValue() {
        Glide.with(this).load(movieModel.getPosterPath()).into(imgPoster);
        Glide.with(this).load(movieModel.getBackdropPath()).into(imgLogo);
        adult.setText(movieModel.getAdultValue());
        language.setText(movieModel.getOriginalLanguage());
        overView.setText(movieModel.getOverView());
        popularity.setText(String.valueOf( movieModel.getPopularity() ));
        title.setText(movieModel.getTitle());
        releaseDate.setText(movieModel.getReleaseDate());
        hasVideo.setText(movieModel.getVideoValue());
        voteCount.setText(String.valueOf((int)movieModel.getVoteCount()));
        ratingBar.setRating((float) movieModel.getVoteAverage());
    }
}
