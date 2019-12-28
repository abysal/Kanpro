package com.example.collegeapp.Model;

public class Notice {
    private String id,notice_title,notice_subject,notice_desc,notice_file,notice_date;

    public Notice(String id, String notice_title, String notice_subject, String notice_desc, String notice_file, String notice_date) {
        this.id = id;
        this.notice_title = notice_title;
        this.notice_subject = notice_subject;
        this.notice_desc = notice_desc;
        this.notice_file = notice_file;
        this.notice_date = notice_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_subject() {
        return notice_subject;
    }

    public void setNotice_subject(String notice_subject) {
        this.notice_subject = notice_subject;
    }

    public String getNotice_desc() {
        return notice_desc;
    }

    public void setNotice_desc(String notice_desc) {
        this.notice_desc = notice_desc;
    }

    public String getNotice_file() {
        return notice_file;
    }

    public void setNotice_file(String notice_file) {
        this.notice_file = notice_file;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(String notice_date) {
        this.notice_date = notice_date;
    }
}
