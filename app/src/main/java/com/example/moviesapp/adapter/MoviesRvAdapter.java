package com.example.moviesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.pojo.MovieModel;

import java.util.List;

public class MoviesRvAdapter extends RecyclerView.Adapter<MoviesRvAdapter.MoviesViewHolder> {
    private Context mContext;
    private List<MovieModel> moviesList;
    public MoviesRvAdapter(Context context, List<MovieModel> moviesList) {
        mContext = context;
        this.moviesList = moviesList;
    }
    @NonNull
    @Override
    public MoviesRvAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_rv_item,parent,false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesRvAdapter.MoviesViewHolder holder, int position) {
        MovieModel model = moviesList.get(position);
        holder.tvMovieName.setText(model.getMovieName());
        holder.tvMovieRelease.setText(model.getMovieRelease());

        // change here Glide -> fire base

        //holder.imgViewIcon.setImageResource(model.getImageUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Item is Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieName;
        TextView tvMovieRelease;
        ImageView imgViewIcon;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieName = itemView.findViewById(R.id.tv_movie_name);
            tvMovieRelease = itemView.findViewById(R.id.tv_movie_release);
            imgViewIcon = itemView.findViewById(R.id.item_icon);
        }
    }
}
