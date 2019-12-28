package com.example.collegeapp.Activity;

import android.content.Context;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.collegeapp.API.UserAPI;
import com.example.collegeapp.Model.User;
import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateProfile extends AppCompatActivity {
    ActionBar actionBar;

    EditText et_first_name, et_last_name, et_batch,et_email,et_user_name,et_password,et_section;
    Button btn_update;
    RadioGroup rg_gender;
    RadioButton rb_male,rb_female;
    UserAPI userAPI;
    String getGender;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Update Profile");

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

        btn_update=(Button)findViewById(R.id.btn_update);

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

        preferences =getSharedPreferences("Userinfo", Context.MODE_PRIVATE);

        String id1 = preferences.getString("id", "");
        String first_name1 = preferences.getString("first_name", "");
        String last_name1 = preferences.getString("last_name", "");
        String email1 = preferences.getString("email", "");
        String gender1 = preferences.getString("gender", "");
        String batch1 = preferences.getString("batch", "");
        String section1 = preferences.getString("section", "");
        String user_name1 = preferences.getString("user_name", "");

        et_first_name.setText(first_name1);
        et_last_name.setText(last_name1);
        et_email.setText(email1);

        if (gender1.equals("Male")){
            rg_gender.check(R.id.rb_male);
        }
        else if (gender1.equals("Female")){
            rg_gender.check(R.id.rb_female);
        }

        et_batch.setText(batch1);
        et_section.setText(section1);
        et_user_name.setText(user_name1);
        et_password.setText("");


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    Intent registerIntent=new Intent(UpdateProfile.this, Dashboard.class);
                    registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    updateUser();

           startActivity(registerIntent);
                    finish();
                }
            }
        });

    }
    public void createInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userAPI=retrofit.create(UserAPI.class);
    }

    public void updateUser(){
        createInstance();
        User user=new User(et_first_name.getText().toString(),et_last_name.getText().toString(),
                et_email.getText().toString(),et_batch.getText().toString(),et_section.getText().toString(),
                getGender,"",
                et_user_name.getText().toString(),et_password.getText().toString());

        Call<Void> addCall=userAPI.updateUser(preferences.getString("id",""),user);
        addCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(getApplicationContext(), "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "User Updated Successfully", Toast.LENGTH_SHORT).show();
                preferences=getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                editor=preferences.edit();
                editor.putString("first_name",et_first_name.getText().toString()).commit();
                editor.putString("last_name",et_last_name.getText().toString()).commit();
                editor.putString("email",et_email.getText().toString()).commit();
                editor.putString("gender",getGender);
                editor.putString("batch",et_batch.getText().toString()).commit();
                editor.putString("section",et_section.getText().toString()).commit();
                editor.putString("user_name",et_user_name.getText().toString()).commit();
                editor.putString("password",et_password.getText().toString()).commit();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to update user", Toast.LENGTH_SHORT).show();
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
