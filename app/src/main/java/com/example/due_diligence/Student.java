package com.example.due_diligence;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Student implements Serializable {
    String name;
    String email;
    String password;
    List<Projects> projects;
    List<Notification> notification;
    List<Request> request;

    public Student() {
    }

    public Student(String name, String email, String password, List<Projects> projects, List<Notification> notification, List<Request> request) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.projects = projects;
        this.notification = notification;
        this.request = request;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Projects> getProjects() {
        return projects;
    }

    public void setProjects(List<Projects> projects) {
        this.projects = projects;
    }

    public List<Notification> getNotifications() {
        return notification;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notification = notifications;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }
}
