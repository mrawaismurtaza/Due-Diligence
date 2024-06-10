package com.example.due_diligence;

import java.io.Serializable;
import java.util.List;

public class Projects implements Serializable {
    private String image;
    private String member;
    private String name;
    private int numberOfSubmissions;
    private String supervisor;
    private List<Tasks> tasks;

    public Projects() {}

    public Projects(String image, String member, String name, int numberOfSubmissions, String supervisor, List<Tasks> tasks) {
        this.image = image;
        this.member = member;
        this.name = name;
        this.numberOfSubmissions = numberOfSubmissions;
        this.supervisor = supervisor;
        this.tasks = tasks;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSubmissions() {
        return numberOfSubmissions;
    }

    public void setNumberOfSubmissions(int numberOfSubmissions) {
        this.numberOfSubmissions = numberOfSubmissions;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }
}