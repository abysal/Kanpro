package com.example.collegeapp.Model;

public class Course {
    private String id,course_name,course_price,course_duration,course_modules,course_desc,course_image;

    public Course(String id, String course_name, String course_price, String course_duration, String course_modules, String course_desc, String course_image) {
        this.id = id;
        this.course_name = course_name;
        this.course_price = course_price;
        this.course_duration = course_duration;
        this.course_modules = course_modules;
        this.course_desc = course_desc;
        this.course_image = course_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_price() {
        return course_price;
    }

    public void setCourse_price(String course_price) {
        this.course_price = course_price;
    }

    public String getCourse_duration() {
        return course_duration;
    }

    public void setCourse_duration(String course_duration) {
        this.course_duration = course_duration;
    }

    public String getCourse_modules() {
        return course_modules;
    }

    public void setCourse_modules(String course_modules) {
        this.course_modules = course_modules;
    }

    public String getCourse_desc() {
        return course_desc;
    }

    public void setCourse_desc(String course_desc) {
        this.course_desc = course_desc;
    }

    public String getCourse_image() {
        return course_image;
    }

    public void setCourse_image(String course_image) {
        this.course_image = course_image;
    }
}
