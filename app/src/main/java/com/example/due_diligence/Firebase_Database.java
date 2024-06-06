package com.example.due_diligence;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase_Database {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://pmd-se-a-java-ef1ca-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("");


    public void createUser(String email, String password) {
        // create new user data in firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        user = mAuth.getCurrentUser();
                    } else {
                        // if fails show error

                    }


                });

    }

    public void loginUser(String emailtxt, String passwordtxt) {
        // login user
        mAuth.signInWithEmailAndPassword(emailtxt, passwordtxt)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        user = mAuth.getCurrentUser();
                    } else {
                        // if fails show error
                    }
                });
    }
}