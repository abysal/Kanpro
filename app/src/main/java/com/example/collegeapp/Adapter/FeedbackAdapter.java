package com.example.collegeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.API.FeedbackAPI;
import com.example.collegeapp.Activity.Dashboard;
import com.example.collegeapp.Model.FeedbackViewResponse;
import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.SubmissionHolder> {
    List<FeedbackViewResponse> feedbackViewResponseList;
    Context context;
    SharedPreferences preferences;
   FeedbackAPI feedbackAPI;
   String feedback_id;
   String sdate;


    public FeedbackAdapter(List<FeedbackViewResponse> feedbackViewResponses, Context context) {
        this.feedbackViewResponseList = feedbackViewResponses;
        this.context = context;
    }

    @NonNull
    @Override
    public SubmissionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_feedback_row,viewGroup,false);
        return new FeedbackAdapter.SubmissionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmissionHolder submissionHolder, int i) {
        final FeedbackViewResponse feedback=feedbackViewResponseList.get(i);
        feedback_id=feedback.get_id();
        submissionHolder.feedback.setText("Feedback:"+feedback.getFeedback());
        String Sdate=feedback.getCreatedAt();
        SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat=new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss",Locale.getDefault());
        try {
            sdate=outputFormat.format(inputFormat.parse(Sdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
          submissionHolder.sdate.setText("Date:"+sdate);
            submissionHolder.btn_delete_feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteSubmission();
                }
            });

    }

    @Override
    public int getItemCount() {
        return feedbackViewResponseList.size();
    }

    public class SubmissionHolder extends RecyclerView.ViewHolder{
        public TextView feedback,sdate;
        public Button btn_delete_feedback;


        public SubmissionHolder(@NonNull View itemView) {
            super(itemView);
            sdate=itemView.findViewById(R.id.sdate);
            feedback=itemView.findViewById(R.id.feedback);
            btn_delete_feedback=itemView.findViewById(R.id.btn_delete_feedback);
        }
    }


    public void deleteSubmission(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        feedbackAPI=retrofit.create(FeedbackAPI.class);
       Call<Void> listCall=feedbackAPI.deleteFeedback(feedback_id);
        listCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(context, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context, "Feedback Deleted Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
