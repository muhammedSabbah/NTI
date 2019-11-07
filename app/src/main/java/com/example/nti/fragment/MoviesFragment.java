package com.example.nti.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nti.R;
import com.example.nti.adapter.MoviesRvAdapter;
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

public class MoviesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MoviesRvAdapter adapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Map<String, Object> moviesmap = new HashMap<>();

    private List<MovieModel> movieModelList = new ArrayList<>();
    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = view.findViewById(R.id.rv_movies);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMoviesFromFirebase();
    }

    private void setRecyclerView(List<MovieModel> movieModelList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MoviesRvAdapter(getContext(), movieModelList);
        recyclerView.setAdapter(adapter);
    }

    private void getMoviesFromFirebase() {
        db.collection("Movies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            movieModelList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                moviesmap = document.getData();
                                if(getMovie(moviesmap) != null){
                                    MovieModel movieModel = getMovie(moviesmap);
                                    movieModelList.add(movieModel);
                                }
                            }
                            setRecyclerView(movieModelList);
                        } else {

                        }
                    }
                });
    }

    private MovieModel getMovie(Map<String, Object> moviesmap){
        try {
            MovieModel movieModel = new MovieModel();
            movieModel.setMovieName(moviesmap.get("movieName").toString());
            movieModel.setMovieRelease(moviesmap.get("movieRelease").toString());
            movieModel.setImageUrl(moviesmap.get("imageUrl").toString());
            return movieModel;
        }catch (Exception e){
            return null;
        }
    }
}
