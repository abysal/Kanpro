package com.example.collegeapp.API;

import com.example.collegeapp.Model.LoginResponse;
import com.example.collegeapp.Model.RegisterResponse;
import com.example.collegeapp.Model.User;
import com.example.collegeapp.Model.UserImage;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserAPI {
    @POST("users/register")
    Call<RegisterResponse> addUser(@Body User user);

    @POST("users/login")
    Call<LoginResponse> getUser(@Body User user);

    /*@Multipart
    @PUT("uploads/updateUserImage/{id}")
    Call<Void> uploadImage(@Path("id") String id,@Part MultipartBody.Part img);*/

    @Multipart
    @POST("uploadImage")
    Call<UserImage> uploadImage(@Part MultipartBody.Part img);

    @PUT("users/updateImage/{id}")
    Call<Void> updateImage(@Path("id") String id,@Body UserImage userImage);

    @PUT("users/updateUserAndroid/{id}")
    Call<Void> updateUser(@Path("id") String id,@Body User user);

    /*@Multipart
    @Header("Cookie")String cookie,
    @PUT("/uploads/updateUserImage/{id}")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part file, @Path("id") String id);*/
}
