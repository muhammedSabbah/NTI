package com.example.nti.fragment.mainFragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nti.R;
import com.example.nti.adapter.MoviesRvAdapter;
import com.example.nti.mvp.MovieModelPresenter;
import com.example.nti.mvp.MovieView;
import com.example.nti.pojo.MovieModel;

import java.util.List;

public class MoviesFragment extends Fragment implements MovieView {

    private RecyclerView recyclerView;
    private MoviesRvAdapter adapter;

    private ImageView imgRocket;
    private TextView loading;
    private Animation animation;

    MovieModelPresenter movieModelPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = view.findViewById(R.id.rv_movies);
        imgRocket = view.findViewById(R.id.iv_rocket);
        loading = view.findViewById(R.id.tv_load);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        animationCore();

        movieModelPresenter = new MovieModelPresenter(this);
        movieModelPresenter.getMoviesFromFirebase();
    }

    private void animationCore() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.frombottom);
        imgRocket.setAnimation(animation);
        loading.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imgRocket.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setRecyclerView(List<MovieModel> movieModelList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 0));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation()));
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MoviesRvAdapter(getContext(), movieModelList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onGetMovieModel(List<MovieModel> movieModelList) {
        setRecyclerView(movieModelList);
    }
}
