package com.example.collegeapp.Model;

public class SubmissionViewResponse {
    private String _id,user_id,assignment_id,assignment_title,assignment_links,assignment_file_user,assignment_submitted_date,u_id,assign_id,createdAt,updatedAt;

    public SubmissionViewResponse(String _id, String user_id, String assignment_id, String assignment_title, String assignment_links, String assignment_file_user, String assignment_submitted_date, String u_id, String assign_id, String createdAt, String updatedAt) {
        this._id = _id;
        this.user_id = user_id;
        this.assignment_id = assignment_id;
        this.assignment_title = assignment_title;
        this.assignment_links = assignment_links;
        this.assignment_file_user = assignment_file_user;
        this.assignment_submitted_date = assignment_submitted_date;
        this.u_id = u_id;
        this.assign_id = assign_id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAssignment_id() {
        return assignment_id;
    }

    public void setAssignment_id(String assignment_id) {
        this.assignment_id = assignment_id;
    }

    public String getAssignment_title() {
        return assignment_title;
    }

    public void setAssignment_title(String assignment_title) {
        this.assignment_title = assignment_title;
    }

    public String getAssignment_links() {
        return assignment_links;
    }

    public void setAssignment_links(String assignment_links) {
        this.assignment_links = assignment_links;
    }

    public String getAssignment_file_user() {
        return assignment_file_user;
    }

    public void setAssignment_file_user(String assignment_file_user) {
        this.assignment_file_user = assignment_file_user;
    }

    public String getAssignment_submitted_date() {
        return assignment_submitted_date;
    }

    public void setAssignment_submitted_date(String assignment_submitted_date) {
        this.assignment_submitted_date = assignment_submitted_date;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getAssign_id() {
        return assign_id;
    }

    public void setAssign_id(String assign_id) {
        this.assign_id = assign_id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
