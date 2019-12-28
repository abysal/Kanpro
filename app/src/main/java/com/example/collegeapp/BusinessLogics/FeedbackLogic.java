package com.example.collegeapp.BusinessLogics;

import com.example.collegeapp.API.FeedbackAPI;
import com.example.collegeapp.Model.Feedback;
import com.example.collegeapp.Model.SubmissionResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.collegeapp.Url.Url.BASE_URL;

public class FeedbackLogic {
    private String u_id,feedback1,feedback_id;
    boolean isSuccess=false;
    FeedbackAPI feedbackAPI;
    public static String message_success_fed;

    public FeedbackLogic(String u_id, String feedback1) {
        this.u_id = u_id;
        this.feedback1 = feedback1;
    }

    public void createInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        feedbackAPI=retrofit.create(FeedbackAPI.class);
    }

    public boolean addFeedback(){
        createInstance();
        Feedback feedback=new Feedback(u_id,feedback1);
        Call<SubmissionResponse> userCall=feedbackAPI.submitFeedback(feedback);
        try{
            Response<SubmissionResponse> registerResponseResponse=userCall.execute();
            if (registerResponseResponse.body().getSuc_message()!=null){
                isSuccess=true;
                message_success_fed=registerResponseResponse.body().getSuc_message();
            }
            else {
                isSuccess=false;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
