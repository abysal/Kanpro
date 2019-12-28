package com.example.collegeapp.Model;

public class DeleteFeedback {
    private String feedback_id;

    public DeleteFeedback(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }
}
