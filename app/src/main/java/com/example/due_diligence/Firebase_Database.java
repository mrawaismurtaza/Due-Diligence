package com.example.due_diligence;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Firebase_Database {


    //Could Storage

    private StorageReference storageReference = FirebaseStorage.getInstance("gs://pmd-se-a-java-ef1ca.appspot.com").getReference();

    public void sentProposal(String email, Uri fileUri, View view) {

        if (fileUri != null) {
            if (email != null) {
                String sanitizedEmail = email.replace(".", "_");
                StorageReference fileReference = storageReference.child("proposals/" + sanitizedEmail + "/" + fileUri.getLastPathSegment());

                fileReference.putFile(fileUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Snackbar.make(view, "Proposal Uploaded", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(view, "Failed to upload proposal", Snackbar.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Snackbar.make(view, "No email provided", Snackbar.LENGTH_SHORT).show();
            }

        } else {
            Snackbar.make(view, "No file selected", Snackbar.LENGTH_SHORT).show();
        }
    }



    //RealTime Database

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://pmd-se-a-java-ef1ca-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("Users");

    public void getStudentDetails(String userEmail, Firebase_Database.StudentCallback studentCallback) {
        myRef.child(userEmail.replace(".", "_")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student student = snapshot.child("Student").child(userEmail.replace(".", "_")).getValue(Student.class);
                studentCallback.onStudentResult(student);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase_Database", "Error: " + error.getMessage());
            }
        });
    }

    public void getTeacher(String email, Firebase_Database.SingleTeacherCallback singleTeacherCallback) {
        myRef.child(email.replace(".", "_")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Teacher teacher = snapshot.child("Teacher").child(email.replace(".", "_")).getValue(Teacher.class);
                singleTeacherCallback.onTeacherResult(teacher);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase_Database", "Error: " + error.getMessage());
            }
        });

    }

    public void loginUser(String emailtxt, String passwordtxt, Firebase_Database.LoginCallback loginCallback) {
        //replace emailtxt @ to _ and . to _
        emailtxt = emailtxt.replace(".", "_");
        emailtxt = emailtxt.replace("@", "_");

        String finalEmailtxt = emailtxt;
        myRef.child("teacher").child(finalEmailtxt).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String password = snapshot.child("password").getValue(String.class);
                    if (password.equals(passwordtxt)) {
                        loginCallback.onLoginResult(true);
                    } else {
                        loginCallback.onLoginResult(false);
                    }
                } else {
                    myRef.child("student").child(finalEmailtxt).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String password = snapshot.child("password").getValue(String.class);
                                if (password.equals(passwordtxt)) {
                                    loginCallback.onLoginResult(true);
                                } else {
                                    loginCallback.onLoginResult(false);
                                }
                            } else {
                                loginCallback.onLoginResult(false);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("Firebase_Database", "Error: " + error.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase_Database", "Error: " + error.getMessage());
            }
        });
    }

    public void signupUser(String emailtxt, String passwordtxt, String nametxt, String role, String s, View view, Firebase_Database.SignupCallback userAlreadyExists) {
        //replace emailtxt @ to _ and . to _
        emailtxt = emailtxt.replace(".", "_");
        emailtxt = emailtxt.replace("@", "_");

        String finalEmailtxt = emailtxt;
        myRef.child(role).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(finalEmailtxt).exists()) {
                    userAlreadyExists.onSignupResult(false);
                } else {
                    myRef.child(role).child(finalEmailtxt).child("name").setValue(nametxt);
                    myRef.child(role).child(finalEmailtxt).child("password").setValue(passwordtxt);
                    myRef.child(role).child(finalEmailtxt).child("email").setValue(finalEmailtxt);
                    myRef.child(role).child(finalEmailtxt).child("profile").setValue(s);
                    myRef.child(role).child(finalEmailtxt).child("projects");
                    myRef.child(role).child(finalEmailtxt).child("notification");
                    userAlreadyExists.onSignupResult(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase_Database", "Error: " + error.getMessage());
            }
        });
    }


    public interface StudentCallback {
        void onStudentResult(Student student);
    }


    public interface SingleTeacherCallback {
        void onTeacherResult(Teacher teacher);
    }

    public interface LoginCallback {
        void onLoginResult(boolean success);
    }

    public interface SignupCallback {
        void onSignupResult(boolean success);
    }
}
