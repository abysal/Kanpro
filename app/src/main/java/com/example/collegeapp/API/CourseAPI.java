package com.example.collegeapp.API;

import com.example.collegeapp.Model.Course;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CourseAPI {
    @Multipart
    @POST("upload")
    Call<Course> uploadImage(@Part MultipartBody.Part body);

    @POST("item/additem")
    Call<Void> addCourse(@Body Course course);

    @GET("courses")
    Call<List<Course>> getAllCourse();
}
