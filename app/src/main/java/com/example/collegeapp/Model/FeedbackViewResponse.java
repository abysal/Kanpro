package com.example.collegeapp.Model;

public class FeedbackViewResponse {
    private String _id,feedback,createdAt;

    public FeedbackViewResponse(String _id, String feedback, String createdAt) {
        this._id = _id;
        this.feedback = feedback;
        this.createdAt = createdAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
