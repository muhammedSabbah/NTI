package com.example.nti.mvp;

import androidx.annotation.NonNull;

import com.example.nti.pojo.MovieModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieModelPresenter {
    private MovieView movieView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<MovieModel> movieModelList = new ArrayList<>();
    private Map<String, Object> moviesmap = new HashMap<>();

    public MovieModelPresenter(MovieView movieView) {
        this.movieView = movieView;
    }

    public void getMoviesFromFirebase() {
        db.collection("Movies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                moviesmap = document.getData();
                                if (getMovie(moviesmap) != null) {
                                    MovieModel movieModel = getMovie(moviesmap);
                                    movieModelList.add(movieModel);
                                }
                                movieView.onGetMovieModel(movieModelList);
                            }
                        }
                    }
                });
    }

    private MovieModel getMovie(Map<String, Object> moviesmap) {
        try {
            MovieModel movieModel = new MovieModel();
            movieModel.setAdult((boolean) moviesmap.get("adult"));
            movieModel.setBackdropPath(moviesmap.get("backdrop_path").toString());
            movieModel.setOriginalLanguage(moviesmap.get("original_language").toString());
            movieModel.setOverView(moviesmap.get("overview").toString());
            movieModel.setPopularity((double) moviesmap.get("popularity"));
            movieModel.setPosterPath(moviesmap.get("poster_path").toString());
            movieModel.setReleaseDate(moviesmap.get("release_date").toString());
            movieModel.setTitle(moviesmap.get("title").toString());
            movieModel.setHasVideo((boolean) moviesmap.get("video"));
            movieModel.setVoteAverage((double) moviesmap.get("vote_average"));
            movieModel.setVoteCount((long) moviesmap.get("vote_count"));
            return movieModel;
        } catch (Exception e) {
            return null;
        }
    }
}
