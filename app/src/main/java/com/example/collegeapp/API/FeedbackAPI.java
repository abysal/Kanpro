package com.example.collegeapp.API;

import com.example.collegeapp.Model.Feedback;
import com.example.collegeapp.Model.FeedbackViewResponse;
import com.example.collegeapp.Model.Submission;
import com.example.collegeapp.Model.SubmissionResponse;
import com.example.collegeapp.Model.SubmissionViewResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FeedbackAPI {

    @POST("feedbacks/feedbacks/Android")
    Call<SubmissionResponse> submitFeedback(@Body Feedback feedback);

    @GET("feedbacks/Android/{id}")
    Call<List<FeedbackViewResponse>> getMyFeedback(@Path("id") String id);

    @DELETE("feedbacks/deleteFeedback/Android/{id}")
    Call<Void> deleteFeedback(@Path("id") String id);
}
