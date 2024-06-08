package com.example.due_diligence;

public class Student {
    String name;
    String email;
    String password;
    Integer numberOfNoti;
    Projects projects;
    Notification notification;
    Request request;


    public Student(String name, String email, String password, Integer numberOfNoti, Projects projects, Notification notification, Request request) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberOfNoti = numberOfNoti;
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

    public Integer getNumberOfNoti() {
        return numberOfNoti;
    }

    public void setNumberOfNoti(Integer numberOfNoti) {
        this.numberOfNoti = numberOfNoti;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public Notification getNotifications() {
        return notification;
    }

    public void setNotifications(Notification notifications) {
        this.notification = notification;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
