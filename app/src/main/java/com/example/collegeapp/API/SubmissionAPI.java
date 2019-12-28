package com.example.collegeapp.API;

import com.example.collegeapp.Model.Assignment;
import com.example.collegeapp.Model.Submission;
import com.example.collegeapp.Model.SubmissionResponse;
import com.example.collegeapp.Model.SubmissionViewResponse;
import com.example.collegeapp.Model.User;
import com.example.collegeapp.Model.UserImage;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface SubmissionAPI {
    /*@Multipart
    @POST("fileSubmit/assignmentSubmit")
    Call<SubmissionResponse> uploadFile(@PartMap Map<String, RequestBody> map);*/

    @Multipart
    @POST("fileSubmit/assignmentSubmit")
    Call<SubmissionResponse> uploadFile(@Part MultipartBody.Part myfile);

    @POST("submissions/addSubmissionAndroid")
    Call<SubmissionResponse> submitAssignment(@Body Submission submission);

    @Multipart
    @POST("fileSubmit/assignmentSubmit")
    Call<SubmissionResponse> updateFile(@Part MultipartBody.Part myfile);

    @PUT("submissions/updateSubmissionAndroid/{id}")
    Call<SubmissionResponse> updateSubmission(@Path("id") String id, @Body Submission submission);

    /*@PUT("users/updateImage/{id}")
    Call<Void> updateImage(@Path("id") String id, @Body UserImage userImage);

    @PUT("users/updateUserAndroid/{id}")
    Call<Void> updateUser(@Path("id") String id,@Body User user);*/

    @GET("submissions/submissions/Android")
    Call<List<SubmissionViewResponse>> getAllSubmissions();

    @GET("submissions/android/{id}")
    Call<List<SubmissionViewResponse>> getMySubmissions(@Path("id") String id);

    @DELETE("submissions/deleteSubmission/Android/{id}")
    Call<Void> deleteSubmission(@Path("id") String id);

}
