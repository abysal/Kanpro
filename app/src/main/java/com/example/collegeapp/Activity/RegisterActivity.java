package com.example.collegeapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.collegeapp.BusinessLogics.RegisterLogic;
import com.example.collegeapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_first_name, et_last_name, et_batch,et_email,et_user_name,et_password,et_section;
    Button btn_register;
    RadioGroup rg_gender;
    RadioButton rb_male,rb_female;
    String getGender;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        actionBar=getSupportActionBar();
        actionBar.setTitle("College App");
        actionBar.setSubtitle("Register");
        initViews();
    }

    public void initViews(){
        et_first_name=(EditText)findViewById(R.id.et_first_name);
        et_last_name=(EditText)findViewById(R.id.et_last_name);
        et_email=(EditText)findViewById(R.id.et_email);
        rg_gender=(RadioGroup)findViewById(R.id.rg_gender);
        rb_male=(RadioButton)findViewById(R.id.rb_male);
        rb_female=(RadioButton)findViewById(R.id.rb_female);
        et_batch=(EditText)findViewById(R.id.et_batch);
        et_section=(EditText)findViewById(R.id.et_section);
        et_user_name=(EditText)findViewById(R.id.et_user_name);
        et_password=(EditText)findViewById(R.id.et_password);
        getGender=rb_male.getText().toString();

        btn_register=(Button)findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rb_male){
                    getGender=rb_male.getText().toString();
                }
                else if(checkedId==R.id.rb_female){
                    getGender=rb_female.getText().toString();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                if (validate()){
                    addUser();
                }

        }

    }
    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
    public void addUser(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Registering User...");
        pd.setCancelable(false);
        pd.show();
        final RegisterLogic registerLogic=new RegisterLogic(et_first_name.getText().toString(),
                et_last_name.getText().toString(),et_email.getText().toString(),et_batch.getText().toString(),
                et_section.getText().toString(),getGender,"",
                et_user_name.getText().toString(),
                et_password.getText().toString());
        StrictMode();

        if (registerLogic.addUser()) {
            Toast.makeText(RegisterActivity.this, RegisterLogic.message_success_reg, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            pd.dismiss();
            finish();
        }
        else if(RegisterLogic.message_error_reg!=null) {
            Toast.makeText(RegisterActivity.this, RegisterLogic.message_error_reg, Toast.LENGTH_SHORT).show();
            et_email.setError("Enter Another Email");
            et_email.requestFocus();
            pd.dismiss();

        }
        else {
            Toast.makeText(this, RegisterLogic.message_reg, Toast.LENGTH_SHORT).show();
        }


    }

    private boolean validate(){
        if(TextUtils.isEmpty(et_first_name.getText().toString())){
            et_first_name.setError("Enter First Name");
            et_first_name.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(et_last_name.getText().toString())){
            et_last_name.setError("Enter Last Name");
            et_last_name.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(et_email.getText().toString())){
            et_email.setError("Enter Email");
            et_email.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(et_batch.getText().toString())){
            et_batch.setError("Enter Batch");
            et_batch.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(et_section.getText().toString())){
            et_section.setError("Enter Section");
            et_section.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(et_user_name.getText().toString())){
            et_user_name.setError("Enter User Name");
            et_user_name.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(et_password.getText().toString())){
            et_password.setError("Enter password");
            et_password.requestFocus();
            return false;
        }
        if(et_password.length()<6){
            et_password.setError("Password should be at least 6 characters");
            et_password.requestFocus();
            return false;
        }

        return true;
    }
}
