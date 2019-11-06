package com.example.moviesapp.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moviesapp.R;
import com.example.moviesapp.adapter.MoviesRvAdapter;
import com.example.moviesapp.pojo.MovieModel;
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

    //Declare an instance of FirebaseFirestore
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Map<String, Object> mapMoviesFireStore = new HashMap<>();

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
                            Toast.makeText(getContext(), " Get Movies", Toast.LENGTH_SHORT).show();
                            Log.d("test", task.toString());
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(getContext(), " Get narden", Toast.LENGTH_SHORT).show();
                                mapMoviesFireStore = document.getData();
                                MovieModel movieModel = getMovie(mapMoviesFireStore);
                                movieModelList.add(movieModel);
                            }

                            setRecyclerView(movieModelList);
                        } else {
                            Toast.makeText(getContext(), "Error Get Movies", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private MovieModel getMovie(Map<String, Object> mapMoviesFireStore) {
        MovieModel movieModel = new MovieModel();
        movieModel.setMovieName(mapMoviesFireStore.get("movieName").toString());
        movieModel.setMovieRelease(mapMoviesFireStore.get("movieRelease").toString());
        movieModel.setImageUrl(mapMoviesFireStore.get("imageUrl").toString());
        return movieModel;
    }
}
