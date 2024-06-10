package com.example.due_diligence;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class RequestProject extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;
    EditText TeacherEmail, memberEmail, projectName;
    Firebase_Database database;
    Student student;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_project);
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");

        TeacherEmail = findViewById(R.id.teacheredt);
        memberEmail = findViewById(R.id.memberedt);
        projectName = findViewById(R.id.projectnameedt);

        database = new Firebase_Database();
    }









    public void Upload_Proposal(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileUri = data.getData();
            Toast.makeText(this, "File Selected: " + fileUri.getPath(), Toast.LENGTH_SHORT).show();
        }
    }

    public void Sent_Propsal(View view) {
//        database.getTeacher(TeacherEmail.getText().toString() , new Firebase_Database.SingleTeacherCallback() {
//            @Override
//            public void onTeacherResult(Teacher teacher) {
//                List<Projects> projects = new ArrayList<>();
//                List<String> tasks = new ArrayList<>();
//                tasks.add("Add some Funtionality");
//                projects.add(new Projects(projectName.getText().toString(), memberEmail.getText().toString(), teacher.getName().toString(), tasks , 0, R.drawable.profile_icon));
//                student.setProjects(projects);
//                List<Request> requests = new ArrayList<>();
//                requests.add( new Request(projectName.getText().toString(), memberEmail.getText().toString()));
//                teacher.setRequests(requests);
//                database.updateTeacher(teacher);
//            }
//        });
    }

}
