package com.example.due_diligence;

public class Projects {
    String name;
    String[] members;
    String Supervisor;
    String[] tasks;
    Integer noOfSubmissions;
    int image;

    public Projects(String name, String[] members, String supervisor, String[] tasks, Integer noOfSubmissions, int image) {
        this.name = name;
        this.members = members;
        Supervisor = supervisor;
        this.tasks = tasks;
        this.noOfSubmissions = noOfSubmissions;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public String getSupervisor() {
        return Supervisor;
    }

    public void setSupervisor(String supervisor) {
        Supervisor = supervisor;
    }

    public String[] getTasks() {
        return tasks;
    }

    public void setTasks(String[] tasks) {
        this.tasks = tasks;
    }

    public Integer getNoOfSubmissions() {
        return noOfSubmissions;
    }

    public void setNoOfSubmissions(Integer noOfSubmissions) {
        this.noOfSubmissions = noOfSubmissions;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
