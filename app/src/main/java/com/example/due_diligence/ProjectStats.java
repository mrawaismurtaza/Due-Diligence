package com.example.due_diligence;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ProjectStats extends AppCompatActivity {

    TextView project_title;
    TextView no_of_submissions;
    TextView assigned;
    Projects project;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_stats);

        Intent intent = getIntent();
        project = (Projects) intent.getSerializableExtra("project");


        project_title = findViewById(R.id.projtitletxt);
        no_of_submissions = findViewById(R.id.noofsubtxt);
        assigned = findViewById(R.id.assignedtxt);

        setStats(project);
    }

    private void setStats(Projects project) {
        project_title.setText(project.getName());
        no_of_submissions.setText(project.getNumberOfSubmissions() + " Submissions");
        List<Tasks> tasksList = project.getTasks();
        StringBuilder tasksStringBuilder = new StringBuilder();

        for (Tasks task : tasksList) {
            tasksStringBuilder.append(task.getTask()).append("\n");
        }

        assigned.setText(tasksStringBuilder.toString());
    }

    public void Add_Submission(View view) {
    }
}