package com.example.nti.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.nti.R;
import com.example.nti.pojo.MovieModel;
import com.example.nti.ui.MovieActivity;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_rv_item, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesRvAdapter.MoviesViewHolder holder, int position) {
        final MovieModel model = moviesList.get(position);
        holder.tvMovieTitle.setText(model.getTitle());


        Glide.with(mContext).load(model.getBackdropPath()).into(holder.imgMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieActivity.class);
                Bundle B = new Bundle();
                B.putSerializable("model", model);
                intent.putExtras(B);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieTitle;
        ImageView imgMovie;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            imgMovie = itemView.findViewById(R.id.img_movie);
        }
    }
}
