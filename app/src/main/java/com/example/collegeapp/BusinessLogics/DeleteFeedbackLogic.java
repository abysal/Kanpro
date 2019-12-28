package com.example.collegeapp.BusinessLogics;

import android.util.Log;

import com.example.collegeapp.API.FeedbackAPI;
import com.example.collegeapp.Adapter.FeedbackAdapter;
import com.example.collegeapp.Model.DeleteFeedback;
import com.example.collegeapp.Model.Feedback;
import com.example.collegeapp.Model.SubmissionResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.collegeapp.Url.Url.BASE_URL;

public class DeleteFeedbackLogic {
    private String feedback_id;
    boolean isSuccess=false;
    FeedbackAPI feedbackAPI;

    public DeleteFeedbackLogic(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public void createInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        feedbackAPI=retrofit.create(FeedbackAPI.class);
    }

    public boolean deleteFeedback(){
        createInstance();
        Call<Void> voidCall=feedbackAPI.deleteFeedback(feedback_id);
        //Log.d("fedL",feedback_id);
        try{
            Response<Void> voidResponse=voidCall.execute();
            if (voidResponse.body()!=null){
                isSuccess=true;
            }
            else{
                isSuccess=false;
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;

    }
}
