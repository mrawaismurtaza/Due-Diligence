package com.example.due_diligence;

import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;

import com.example.due_diligence.Notification;
import com.example.due_diligence.Projects;
import com.example.due_diligence.Request;
import com.example.due_diligence.Student;
import com.example.due_diligence.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Firebase_Database {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://pmd-se-a-java-ef1ca-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference("Users");

    public List<Tasks> getTasks(DataSnapshot snapshot) {
        List<Tasks> tasks = new ArrayList<>();
        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            String taskValue = dataSnapshot.child("task").getValue(String.class);
            tasks.add(new Tasks(taskValue));
            Log.d("TAG", "Task: " + taskValue);
        }
        return tasks;
    }

    public List<Projects> getProjects(DataSnapshot snapshot) {
        List<Projects> projects = new ArrayList<>();
        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            String image = dataSnapshot.child("image").getValue(String.class);
            String member = dataSnapshot.child("member").getValue(String.class);
            String name = dataSnapshot.child("name").getValue(String.class);
            int noofsub = dataSnapshot.child("numberOfSubmissions").getValue(Integer.class);
            String supervisor = dataSnapshot.child("supervisor").getValue(String.class);
            List<Tasks> tasks = getTasks(dataSnapshot.child("tasks"));
            projects.add(new Projects(image, member, name, noofsub, supervisor, tasks));
            Log.d("TAG", "Project Name: " + name + ", Member: " + member + ", Image: " + image + ", No of Submissions: " + noofsub + ", Supervisor: " + supervisor);
        }
        return projects;
    }

    public List<Request> getRequests(DataSnapshot snapshot) {
        List<Request> requests = new ArrayList<>();
        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            String name = dataSnapshot.child("name").getValue(String.class);
            String studentEmail = dataSnapshot.child("studentEmail").getValue(String.class);
            requests.add(new Request(name, studentEmail));
            Log.d("TAG", "Request Name: " + name + ", Student Email: " + studentEmail);
        }
        return requests;
    }

    public List<Notification> getNotifications(DataSnapshot snapshot) {
        List<Notification> notifications = new ArrayList<>();
        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
            String senderEmail = dataSnapshot.child("senderEmail").getValue(String.class);
            String message = dataSnapshot.child("message").getValue(String.class);
            String title = dataSnapshot.child("title").getValue(String.class);
            notifications.add(new Notification(senderEmail, message, title));
            Log.d("TAG", "Notification: SenderEmail: " + senderEmail + ", Message: " + message + ", Title: " + title);
        }
        return notifications;
    }

    public void getStudent(String email, final ValueEventListener listener) {
        myRef.child("student").child(email).addListenerForSingleValueEvent(listener);
    }

    public void getTeacher(String email, final ValueEventListener listener) {
        myRef.child("teacher").child(email).addListenerForSingleValueEvent(listener);
    }

    public void signupUser(String emailtxt, String passwordtxt, String nametxt, View view, SignupCallback callback) {
        String email = emailtxt.replace(".", "_").replace("@", "_");
        myRef.child("student").child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    callback.onSignupResult(false);
                } else {
                    myRef.child("student").child(email).child("email").setValue(email);
                    myRef.child("student").child(email).child("name").setValue(nametxt);
                    myRef.child("student").child(email).child("password").setValue(passwordtxt);

                    List<Notification> notifications = new ArrayList<>();
                    notifications.add(new Notification("Welcome to Due Diligence", "Welcome to Due Diligence", "Welcome to Due Diligence"));
                    myRef.child("student").child(email).child("notification").setValue(notifications);

                    List<Tasks> tasksList = new ArrayList<>();
                    tasksList.add(new Tasks("Welcome to Due Diligence"));
                    List<Projects> projectsList = new ArrayList<>();
                    projectsList.add(new Projects("", email, "Welcome to Due Diligence", 0, "Welcome to Due Diligence", tasksList));
                    myRef.child("student").child(email).child("projects").setValue(projectsList);

                    List<Request> requests = new ArrayList<>();
                    requests.add(new Request("Welcome to Due Diligence", email));
                    myRef.child("student").child(email).child("requests").setValue(requests);

                    callback.onSignupResult(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase_Database", "Failed to read value.", error.toException());
                callback.onSignupResult(false);
            }
        });
    }

    public void loginUser(String emailtxt, String passwordtxt, View view, LoginCallback callback) {
        String email = emailtxt.replace(".", "_").replace("@", "_");
        myRef.child("student").child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String password = snapshot.child("password").getValue(String.class);
                    String name = snapshot.child("name").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    Log.d("TAG", "Login - password: " + password + ", name: " + name + ", email: " + email);

                    // Check if the password matches
                    if (password != null && password.equals(passwordtxt)) {
                        Log.d("TAG", "User exists - Name: " + name);

                        // Log the values of projects
                        for (DataSnapshot projectSnapshot : snapshot.child("projects").getChildren()) {
                            String projectName = projectSnapshot.child("name").getValue(String.class);
                            String projectMember = projectSnapshot.child("member").getValue(String.class);
                            // Log other project fields as needed
                            Log.d("TAG", "Project Name: " + projectName + ", Member: " + projectMember);
                        }

                        // Log the values of notification and requests
                        DataSnapshot notificationSnapshot = snapshot.child("notification");
                        DataSnapshot requestsSnapshot = snapshot.child("requests");

                        Log.d("TAG", "Notifications: " + notificationSnapshot.getValue());
                        Log.d("TAG", "Requests: " + requestsSnapshot.getValue());

                        // Create the Student object
                        List<Notification> notifications = getNotifications(notificationSnapshot);
                        List<Request> requests = getRequests(requestsSnapshot);
                        Student student = new Student(
                                name,
                                email,
                                password,
                                getProjects(snapshot.child("projects")),
                                getNotifications(snapshot.child("notification")),
                                getRequests(snapshot.child("request"))
                        );
                        Log.d("TAG", "Login - Student Name: " + student.getName());

                        // Callback

                        // Callback with the Student object
                        callback.onLoginResult(student);
                    } else {
                        Log.d("Firebase_Database", "Incorrect password");
                        callback.onLoginResult(null);
                    }
                } else {
                    Log.d("Firebase_Database", "User does not exist");
                    callback.onLoginResult(null);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase_Database", "Failed to read value.", error.toException());
                callback.onLoginResult(null);
            }
        });
    }

    public interface SignupCallback {
        void onSignupResult(boolean success);
    }

    public interface LoginCallback {
        void onLoginResult(Student student);
    }
}
