package com.example.collegeapp.Activity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AssignmentDetails extends AppCompatActivity {
    ActionBar actionBar;
    TextView module_code,module_name,assignment_no,assignment_desc,assignment_file,assignment_deadline,assignment_publish_date;
    String assign_id,fullPath;

    Button btn_addSubmission;
    String deadline,pdate;
    private long downloadID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_details);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Assignment Detail");

        module_code=(TextView)findViewById(R.id.module_code);
        module_name=(TextView)findViewById(R.id.module_name);
        assignment_no=(TextView)findViewById(R.id.assignment_no);
        assignment_desc=(TextView)findViewById(R.id.assignment_desc);
        assignment_file=(TextView)findViewById(R.id.assignment_file);
        assignment_deadline=(TextView)findViewById(R.id.assignment_deadline);
        assignment_publish_date=(TextView)findViewById(R.id.assignment_publish_date);
        btn_addSubmission=(Button)findViewById(R.id.btn_addSubmission);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            assign_id=bundle.getString("id");
            module_code.setText("Module Code: "+bundle.getString("module_code"));
            module_name.setText("Module Name: "+bundle.getString("module_name"));
            assignment_no.setText("Assignment No: "+bundle.getString("assignment_no"));

            String file=bundle.getString("assignment_file");
            fullPath= Url.BASE_URL+file;
            assignment_file.setText("Download Assignment File");

            assignment_desc.setText("Assignment Description: "+bundle.getString("assignment_desc"));

            String deadline_date=bundle.get("assignment_deadline").toString();
            SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault());
            SimpleDateFormat outputFormat=new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss",Locale.getDefault());
            try {
                deadline=outputFormat.format(inputFormat.parse(deadline_date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assignment_deadline.setText("Assignment Deadline:"+deadline);
            String p_date=bundle.getString("assignment_publish_date");
            SimpleDateFormat inputFormat2=new SimpleDateFormat("ddd MMM dd yyyy HH:mm:ss 'Z'", Locale.getDefault());
            SimpleDateFormat outputFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
            try {
                pdate=outputFormat2.format(inputFormat2.parse(p_date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assignment_publish_date.setText("Assignment Publish Date: "+p_date);
        }
        assignment_file.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                startDownload();

            }
        });
        Date curdate=new Date();
        SimpleDateFormat outputFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        String final_curdate=outputFormat2.format(curdate);
//        if (Date.parse(deadline)<=Date.parse(final_curdate)){
            btn_addSubmission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(AssignmentDetails.this,SubmitAssignment.class);
                    intent.putExtra("id",assign_id);
                    startActivity(intent);

                }
            });
        /*}
        else{
            btn_addSubmission.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AssignmentDetails.this, "Deadline Exceeded", Toast.LENGTH_SHORT).show();
                }
            });
        }*/

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

   public void startDownload() {
        DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request mRqRequest = new DownloadManager.Request(
                Uri.parse(fullPath));
        mRqRequest.setDescription("Downloading File");
        mRqRequest.setTitle("Assignment File");
        downloadID=mManager.enqueue(mRqRequest);
        Toast.makeText(AssignmentDetails.this, "Downloading Started...", Toast.LENGTH_SHORT).show();
    }

}
