package com.example.collegeapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collegeapp.BusinessLogics.FeedbackLogic;
import com.example.collegeapp.R;

public class FeedbackActivity extends AppCompatActivity {
    EditText et_feedback;
    Button btn_feedback_submit;
    SharedPreferences preferences;
    public static String u_id;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Post Feedback");
        et_feedback=(EditText)findViewById(R.id.et_feedback);
        btn_feedback_submit=(Button)findViewById(R.id.btn_submit_feedback);
        preferences=getSharedPreferences("Userinfo",MODE_PRIVATE);
        u_id=preferences.getString("id","");

        btn_feedback_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    addFeedback();

                }
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    public void addFeedback(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Submittting Feedback...");
        pd.setCancelable(false);
        pd.show();
        FeedbackLogic feedbackLogic=new FeedbackLogic(u_id,et_feedback.getText().toString());
        StrictMode();
        if (feedbackLogic.addFeedback()){
            if (FeedbackLogic.message_success_fed!=null){
                Toast.makeText(FeedbackActivity.this, FeedbackLogic.message_success_fed, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(FeedbackActivity.this,Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                et_feedback.setText("");
                pd.dismiss();
                finish();
            }
            else{
                Toast.makeText(FeedbackActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        }
        else{
            Toast.makeText(FeedbackActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }

    }


    private boolean validate(){
        if(TextUtils.isEmpty(et_feedback.getText().toString())){
            et_feedback.setError("Enter Feedback");
            et_feedback.requestFocus();
            return false;
        }
        return true;
    }

}
