package com.example.application1.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.application1.R;
import com.example.application1.adapters.RecyclerviewAdapter;
import com.example.application1.pojo.Student;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerviewAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("ahmed", "19"));
        students.add(new Student("mohamed", "20"));
        students.add(new Student("Ali", "21"));

        Adapter= new RecyclerviewAdapter(this, students);
        recyclerView.setAdapter(Adapter);

    }
}
