package com.example.application1.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application1.R;
import com.example.application1.pojo.GithubUserModel;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.StudentViewHolder>{

    private Context mContext;
    private List<GithubUserModel> githubUserModelList;

    public RecyclerviewAdapter(Context mContext, List<GithubUserModel> githubUserModelList) {
        this.mContext = mContext;
        this.githubUserModelList = githubUserModelList;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        final GithubUserModel githubUserModel = githubUserModelList.get(position);
        holder.txtUserName.setText(githubUserModel.getUserName());
        holder.txtUserUrl.setText(githubUserModel.getUserUrl());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, githubUserModel.getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return githubUserModelList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView txtUserName;
        private TextView txtUserUrl;
        private ConstraintLayout constraintLayout;

        public StudentViewHolder(View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txt_user_name);
            txtUserUrl = itemView.findViewById(R.id.txt_user_url);
            constraintLayout = itemView.findViewById(R.id.layout_parent);
        }
    }
}
