package com.example.due_diligence;

import java.io.Serializable;

public class Notification implements Serializable {
    String title;
    String message;
    String senderEmail;

    public Notification(String title, String message, String senderEmail) {
        this.title = title;
        this.message = message;
        this.senderEmail = senderEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }
}
