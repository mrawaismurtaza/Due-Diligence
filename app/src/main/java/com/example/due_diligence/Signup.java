package com.example.due_diligence;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import com.google.android.material.snackbar.Snackbar;

public class Signup extends AppCompatActivity {

    EditText email, password, confPassword, name;
    RadioButton teacherRadioButton, studentRadioButton;
    String role;
    Firebase_Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        database = new Firebase_Database();
        email = findViewById(R.id.signupemailedt);
        password = findViewById(R.id.signuppassedt);
        confPassword = findViewById(R.id.signupconfirmpassedt);
        name = findViewById(R.id.signupnameedt);
        teacherRadioButton = findViewById(R.id.teacherrdbtn);
        studentRadioButton = findViewById(R.id.studentrdbtn);
    }

    public void Continue(View view) {
        String emailtxt = email.getText().toString();
        String passwordtxt = password.getText().toString();
        String confPasswordtxt = confPassword.getText().toString();
        String nametxt = name.getText().toString();

        if (teacherRadioButton.isChecked()) {
            role = "teacher";
        } else if (studentRadioButton.isChecked()) {
            role = "student";
        } else {
            Snackbar.make(view, "Please select a role", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (emailtxt.isEmpty() || passwordtxt.isEmpty() || confPasswordtxt.isEmpty() || nametxt.isEmpty() || role.isEmpty()) {
            Snackbar.make(view, "Please fill all fields", Snackbar.LENGTH_SHORT).show();
            return;
        }

        if (!passwordtxt.equals(confPasswordtxt)) {
            confPassword.setError("Passwords do not match");
            return;
        }

        database.signupUser(emailtxt, passwordtxt, nametxt, view, new Firebase_Database.SignupCallback() {
            @Override
            public void onSignupResult(boolean success) {
                if (success) {
                    Intent intent = new Intent(Signup.this, Login.class);
                    startActivity(intent);
                } else {
                    email.setError("User already exists");
                }
            }
        });
    }
}
