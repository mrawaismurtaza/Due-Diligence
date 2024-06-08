package com.example.due_diligence;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
    String userEmail;
    Firebase_Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");

        database = new Firebase_Database();

        welcomenametxt = findViewById(R.id.welcomenametxt);
        notification = findViewById(R.id.notificationtxt);
        recyclerView = findViewById(R.id.projectrecycler);

        getUserDetails(userEmail);
    }

    private void getUserDetails(String userEmail) {
        database.getStudentDetails(userEmail, new Firebase_Database.StudentCallback() {
            @Override
            public void onStudentResult(Student studentResult) {
                student = studentResult;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setDetails();
                    }
                });
            }
        });
    }

    private void setDetails() {
        if (student != null) {
            welcomenametxt.setText(student.getName());
            notification.setText(student.getNumberOfNoti().toString());

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            projects = (List<Projects>) student.getProjects();
            adapter = new AdapterHomeProjects(this, projects);
            recyclerView.setAdapter(adapter);
        }
    }

    public void Project_Request(View view) {
        Intent intent = new Intent(this, RequestProject.class);
        intent.putExtra("Student", (Serializable) student);
        startActivity(intent);
    }
}
