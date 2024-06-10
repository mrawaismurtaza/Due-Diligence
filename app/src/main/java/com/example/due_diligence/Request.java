package com.example.due_diligence;

import java.io.Serializable;

public class Request implements Serializable {
    String name;
    String studentEmail;

    public Request(String name, String studentEmail) {
        this.name = name;
        this.studentEmail = studentEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
