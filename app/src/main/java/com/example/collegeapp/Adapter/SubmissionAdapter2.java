package com.example.collegeapp.Adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.API.SubmissionAPI;
import com.example.collegeapp.Activity.Dashboard;
import com.example.collegeapp.Activity.UpdateSubmission;
import com.example.collegeapp.Model.SubmissionViewResponse;
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

public class SubmissionAdapter2 extends RecyclerView.Adapter<SubmissionAdapter2.SubmissionHolder> {
    List<SubmissionViewResponse> submissionList;
    Context context;
    SharedPreferences preferences;
    SubmissionAPI submissionAPI;
    String assign_id,sdate;
    String fullPath;


    public SubmissionAdapter2(List<SubmissionViewResponse> submissionList, Context context) {
        this.submissionList = submissionList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubmissionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_submission_row,viewGroup,false);

        return new SubmissionAdapter2.SubmissionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmissionHolder submissionHolder, int i) {
        final  SubmissionViewResponse response=submissionList.get(i);
        /*preferences=context.getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
        String localUserId=preferences.getString("id","");
        String serverUserId=response.getU_id();*/
        assign_id=response.get_id();

            submissionHolder.assignment_title.setText("Title:"+response.getAssignment_title());
            submissionHolder.assignment_links.setText("Links:"+response.getAssignment_links());
            fullPath=Url.BASE_URL+response.getAssignment_file_user();
            submissionHolder.assignment_file_user.setText("File:"+response.getAssignment_file_user());
            String Pdate=response.getAssignment_submitted_date();
        SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        SimpleDateFormat outputFormat=new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss",Locale.getDefault());
        try {
            sdate=outputFormat.format(inputFormat.parse(Pdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
            submissionHolder.assignment_submitted_date.setText("Submitted Date:"+sdate);

            submissionHolder.assignment_file_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadFile();
                }
            });

            submissionHolder.btn_delete_submission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteSubmission();

                    /*final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("College App")
                            .setMessage("Are you sure want to delete this submission?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();*/
                }
            });

            submissionHolder.btn_edit_submission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, UpdateSubmission.class);
                    intent.putExtra("id",response.get_id());
                    intent.putExtra("assignment_title",response.getAssignment_title());
                    intent.putExtra("assignment_links",response.getAssignment_links());
                    intent.putExtra("assign_id",response.getAssign_id());
                    intent.putExtra("assignment_file_user",response.getAssignment_file_user());
                    intent.putExtra("u_id",response.getU_id());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return submissionList.size();
    }

    public class SubmissionHolder extends RecyclerView.ViewHolder{
        public TextView assignment_title;
        public TextView assignment_links;
        public TextView assignment_file_user;
        public TextView assignment_submitted_date;
        public Button btn_edit_submission,btn_delete_submission;


        public SubmissionHolder(@NonNull View itemView) {
            super(itemView);

            assignment_title=itemView.findViewById(R.id.assignment_title_1);
            assignment_links=itemView.findViewById(R.id.assignment_links_1);
            assignment_file_user=itemView.findViewById(R.id.assignment_file_user_1);
            assignment_submitted_date=itemView.findViewById(R.id.assignment_submitted_date_1);
            btn_edit_submission=itemView.findViewById(R.id.btn_edit_submission);
            btn_delete_submission=itemView.findViewById(R.id.btn_delete_submission);
        }
    }


    public void deleteSubmission(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        submissionAPI=retrofit.create(SubmissionAPI.class);
        Call<Void> listCall=submissionAPI.deleteSubmission(assign_id);
        listCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(context, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(context, "Submission Deleted Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void downloadFile(){
        DownloadManager mManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request mRqRequest = new DownloadManager.Request(
                Uri.parse(fullPath));
        mRqRequest.setDescription("Downloading File");
        //mRqRequest.setTitle("Lecture File");
        long downloadID=mManager.enqueue(mRqRequest);
        Toast.makeText(context, "Downloading", Toast.LENGTH_SHORT).show();
    }


}
