package com.example.collegeapp.Model;

public class LoginResponse {
    private String token;
    private String id,first_name,last_name,email,gender,batch,section,user_name,user_type,user_image,password,message;

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getBatch() {
        return batch;
    }

    public String getSection() {
        return section;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getUser_image() {
        return user_image;
    }

    public String getPassword() {
        return password;
    }
    public String getMessage() {
        return message;
    }
}
