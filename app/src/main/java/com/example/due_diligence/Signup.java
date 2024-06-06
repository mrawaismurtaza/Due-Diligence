package com.example.due_diligence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    EditText email, password, confPassword;
    Firebase_Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = new Firebase_Database();
        email = findViewById(R.id.signupemailedt);
        password = findViewById(R.id.signuppassedt);
        confPassword = findViewById(R.id.signupconfirmpassedt);
    }


    public void Continue(View view) {
        String emailtxt = email.getText().toString();
        String passwordtxt = password.getText().toString();
        String confPasswordtxt = confPassword.getText().toString();

        if (emailtxt.isEmpty() || passwordtxt.isEmpty() || confPasswordtxt.isEmpty()) {
            email.setError("Please fill this field");
            password.setError("Please fill this field");
            confPassword.setError("Please fill this field");
        } else {
            if (passwordtxt.equals(confPasswordtxt)) {
                database.createUser(emailtxt, passwordtxt);
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            } else {
                password.setError("Password does not match");
                confPassword.setError("Password does not match");
            }
        }


    }
}