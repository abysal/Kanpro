package com.example.collegeapp.Model;

public class Assignment {
    private String _id,module_code, module_name, assignment_no,assignment_desc, assignment_deadline,assignment_publish_date, assignment_file;

    public Assignment(String _id, String module_code, String module_name, String assignment_no, String assignment_desc, String assignment_deadline, String assignment_publish_date, String assignment_file) {
        this._id = _id;
        this.module_code = module_code;
        this.module_name = module_name;
        this.assignment_no = assignment_no;
        this.assignment_desc = assignment_desc;
        this.assignment_deadline = assignment_deadline;
        this.assignment_publish_date = assignment_publish_date;
        this.assignment_file = assignment_file;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getModule_code() {
        return module_code;
    }

    public void setModule_code(String module_code) {
        this.module_code = module_code;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getAssignment_no() {
        return assignment_no;
    }

    public void setAssignment_no(String assignment_no) {
        this.assignment_no = assignment_no;
    }

    public String getAssignment_desc() {
        return assignment_desc;
    }

    public void setAssignment_desc(String assignment_desc) {
        this.assignment_desc = assignment_desc;
    }

    public String getAssignment_deadline() {
        return assignment_deadline;
    }

    public void setAssignment_deadline(String assignment_deadline) {
        this.assignment_deadline = assignment_deadline;
    }

    public String getAssignment_publish_date() {
        return assignment_publish_date;
    }

    public void setAssignment_publish_date(String assignment_publish_date) {
        this.assignment_publish_date = assignment_publish_date;
    }

    public String getAssignment_file() {
        return assignment_file;
    }

    public void setAssignment_file(String assignment_file) {
        this.assignment_file = assignment_file;
    }
}

