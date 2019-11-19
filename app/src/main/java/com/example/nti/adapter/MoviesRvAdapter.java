package com.example.nti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.nti.R;
import com.example.nti.fragment.registerFragments.SignUpFragment;
import com.example.nti.pojo.MovieModel;

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
        holder.tvMovieTitle.setText(model.getTitle());

        // change here Glide -> fire base
        Glide.with(mContext).load(model.getBackdropPath()).into(holder.imgMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Item is Clicked", Toast.LENGTH_SHORT).show();
                navigateSignUpFragment();
            }
        });
    }
    private void navigateSignUpFragment(){
        SignUpFragment signUpFragment = new SignUpFragment();
        FragmentManager fragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fr_layout, signUpFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
