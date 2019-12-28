package com.example.collegeapp.API;

import com.example.collegeapp.Model.Assignment;
import com.example.collegeapp.Model.Notice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AssignmentAPI {
    @GET("assignments")
    Call<List<Assignment>> getAllAssignment();
}
