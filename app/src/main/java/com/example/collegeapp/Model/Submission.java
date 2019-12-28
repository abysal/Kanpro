package com.example.collegeapp.Model;

public class Submission {
    private String user_id,assignment_id,assignment_title, assignment_links,assignment_file_user;

    public Submission(String user_id, String assignment_id, String assignment_title, String assignment_links, String assignment_file_user) {
        this.user_id = user_id;
        this.assignment_id = assignment_id;
        this.assignment_title = assignment_title;
        this.assignment_links = assignment_links;
        this.assignment_file_user = assignment_file_user;
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
}
