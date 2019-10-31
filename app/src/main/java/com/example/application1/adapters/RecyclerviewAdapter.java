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
import com.example.application1.pojo.Student;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.StudentViewHolder>{

    private Context mContext;
    private List<Student> students;

    public RecyclerviewAdapter(Context _mContext, List<Student> _students){
        this.mContext = _mContext;
        this.students = _students;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        final Student student = students.get(position);
        holder.txtName.setText(student.getName());
        holder.txtAge.setText(student.getAge());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, student.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtAge;
        private ConstraintLayout constraintLayout;

        public StudentViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtAge = itemView.findViewById(R.id.txt_age);
            constraintLayout = itemView.findViewById(R.id.layout_parent);
        }
    }
}
