package com.example.collegeapp.API;

import com.example.collegeapp.Model.Course;
import com.example.collegeapp.Model.Notice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NoticeAPI {
    @GET("notices")
    Call<List<Notice>> getAllNotice();
}
