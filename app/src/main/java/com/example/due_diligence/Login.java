package com.example.due_diligence;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
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

    @SuppressLint("NotConstructor")
    public void Login(View view) {
        String emailtxt = email.getText().toString();
        String passwordtxt = password.getText().toString();

        if (emailtxt.isEmpty() || passwordtxt.isEmpty()) {
            email.setError("Please fill this field");
            password.setError("Please fill this field");
        } else {
            database.loginUser(emailtxt, passwordtxt, new Firebase_Database.LoginCallback() {
                @Override
                public void onLoginResult(boolean success) {
                    if (success) {
                        Intent intent = new Intent(Login.this, HomePage.class);
                        intent.putExtra("email", emailtxt);
                        startActivity(intent);
                    } else {
                        email.setError("Invalid email or password");
                        password.setError("Invalid email or password");
                    }
                }
            });
        }
    }
}
