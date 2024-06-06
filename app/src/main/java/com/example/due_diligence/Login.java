package com.example.due_diligence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText email, password;
    Firebase_Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginemailedt);
        password = findViewById(R.id.loginpassedt);
        database = new Firebase_Database();
    }

    public void Login(View view) {
        String emailtxt = email.getText().toString();
        String passwordtxt = password.getText().toString();

        if (emailtxt.isEmpty() || passwordtxt.isEmpty()) {
            email.setError("Please fill this field");
            password.setError("Please fill this field");
        } else {
            // check if user exists in firebase
            database.loginUser(emailtxt, passwordtxt);
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }
    }
}