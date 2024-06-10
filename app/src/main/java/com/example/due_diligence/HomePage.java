package com.example.due_diligence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import android.view.View;

public class HomePage extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterHomeProjects adapter;
    Student student;
    List<Projects> projects;

    TextView welcomenametxt, notification;
    Firebase_Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");

        Log.d("TAG", "Login" + student.getName());
        database = new Firebase_Database();

        welcomenametxt = findViewById(R.id.welcomenametxt);
        notification = findViewById(R.id.notificationtxt);
        recyclerView = findViewById(R.id.projectrecycler);

        setDetails();
    }



    private void setDetails() {
        if (student != null) {
            welcomenametxt.setText(student.getName().toString());
            Log.d("TAGS", "dsdsds" + student.getName());
            notification.setText(student.notification.size() + " Notifications");

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            projects = (List<Projects>) student.getProjects();
            adapter = new AdapterHomeProjects(this, projects);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            adapter.setOnItemClickListener(new AdapterHomeProjects.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(HomePage.this, ProjectStats.class);
                    intent.putExtra("project", projects.get(position));
                    startActivity(intent);
                }
            });
        }
    }

    public void Project_Request(View view) {
        Intent intent = new Intent(this, RequestProject.class);
        intent.putExtra("Student", (Serializable) student);
        startActivity(intent);
    }
}
