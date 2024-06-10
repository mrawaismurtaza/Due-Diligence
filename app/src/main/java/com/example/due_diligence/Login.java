package com.example.due_diligence;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class Login extends AppCompatActivity {

    EditText email, password;
    Firebase_Database database;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginemailedt);
        password = findViewById(R.id.loginpassedt);
        database = new Firebase_Database();
    }

    @SuppressLint("NotConstructor")
    public void Login(View view) {
        String emailtxt = email.getText().toString();
        String passwordtxt = password.getText().toString();

        if (emailtxt.isEmpty() || passwordtxt.isEmpty()) {
            email.setError("Please fill this field");
            password.setError("Please fill this field");
        } else {
            database.loginUser(emailtxt, passwordtxt, view, new Firebase_Database.LoginCallback() {
                @Override
                public void onLoginResult(Student student1) {
                    Log.d("TAG", "Login" + student1.getName());
                    student = student1;

                    Log.d("TAG", "Login" + student1.getName());
                        Intent intent = new Intent(Login.this, HomePage.class);
                        intent.putExtra("student", student);
                        startActivity(intent);
                }
            });
        }
    }
}
