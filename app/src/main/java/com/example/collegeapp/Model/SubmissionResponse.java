package com.example.collegeapp.Model;

import com.google.gson.annotations.SerializedName;

public class SubmissionResponse {

    @SerializedName("user_file")
    private String user_file;
  private String suc_message,err_message,message;

    public String getUser_file() {
        return user_file;
    }

    public void setUser_file(String user_file) {
        this.user_file = user_file;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getSuc_message() {
        return suc_message;
    }

    public String getErr_message() {
        return err_message;
    }
}
