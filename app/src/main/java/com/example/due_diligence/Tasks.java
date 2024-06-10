package com.example.due_diligence;

import java.io.Serializable;

public class Tasks implements Serializable {
    String task;

    public Tasks(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
