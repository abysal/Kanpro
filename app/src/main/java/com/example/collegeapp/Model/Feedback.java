package com.example.collegeapp.Model;

public class Feedback {
    private String u_id,feedback;

    public Feedback(String u_id, String feedback) {
        this.u_id = u_id;
        this.feedback = feedback;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
