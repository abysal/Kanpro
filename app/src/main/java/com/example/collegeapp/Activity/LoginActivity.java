package com.example.collegeapp.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collegeapp.BusinessLogics.LoginLogic;
import com.example.collegeapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_user_name,et_password;
    Button btn_login,btn_register;
    ActionBar actionBar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Boolean isLoggedIn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        actionBar=getSupportActionBar();
        actionBar.setTitle("College App");
        actionBar.setSubtitle("Login");
        initViews();
    }

    public void initViews(){
        et_user_name=(EditText)findViewById(R.id.et_user_name);
        et_password=(EditText)findViewById(R.id.et_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        btn_register=(Button)findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }
    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if (validate()){
                    final ProgressDialog pd = new ProgressDialog(this);
                    pd.setMessage("Verifying Username and Password...");
                    pd.setCancelable(false);
                    pd.show();
                    final LoginLogic loginLogic=new LoginLogic(et_user_name.getText().toString(),et_password.getText().toString());
                    StrictMode();

                    if (loginLogic.checkUser()){
                        Toast.makeText(LoginActivity.this, "Welcome "+LoginLogic.first_name_l, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,Dashboard.class);
                        preferences=getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                        editor=preferences.edit();
                        isLoggedIn=true;
                        editor.putString("token",LoginLogic.token_l).commit();
                        editor.putString("id",LoginLogic._id_l).commit();
                        editor.putString("first_name",LoginLogic.first_name_l).commit();
                        editor.putString("last_name",LoginLogic.last_name_l).commit();
                        editor.putString("email",LoginLogic.email_l).commit();
                        editor.putString("gender",LoginLogic.gender_l).commit();
                        editor.putString("batch",LoginLogic.batch_l).commit();
                        editor.putString("section",LoginLogic.section_l).commit();
                        editor.putString("user_name",LoginLogic.user_name_l).commit();
                        editor.putString("user_type",LoginLogic.user_type_l).commit();
                        editor.putString("user_image",LoginLogic.user_image_l).commit();
                        editor.putString("password",LoginLogic.password_l).commit();
                        editor.putBoolean("isLoggedIn",isLoggedIn).commit();
                        startActivity(intent);
                        finish();
                        pd.dismiss();
                    }
                    else if (LoginLogic.message_l!=null){
                        Toast.makeText(this, LoginLogic.message_l, Toast.LENGTH_SHORT).show();
                        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            //Toast.makeText(LoginActivity.this, "Vibrating...", Toast.LENGTH_SHORT).show();
                            vib.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            vib.vibrate(1000);
                        }
                        pd.dismiss();
                    }
                    else{
                        Toast.makeText(this, "No response from server", Toast.LENGTH_SHORT).show();
                        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            //Toast.makeText(LoginActivity.this, "Vibrating...", Toast.LENGTH_SHORT).show();
                            vib.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            vib.vibrate(1000);
                        }
                        pd.dismiss();
                    }

                }

                break;

            case R.id.btn_register:
                Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
                registerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                registerIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(registerIntent);
                break;
        }

    }

    private boolean validate(){
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

        return true;
    }
    /*
    public void checkUser(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Verifying Username and Password...");
        pd.setCancelable(false);
        pd.show();
        createInstance();
        Call<LoginResponse> userCall=userAPI.getUser(new User("","","","","","","",
                et_user_name.getText().toString(),et_password.getText().toString()));

        userCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v.vibrate(500);
                    }
                    return;
                }
                else{
                    LoginResponse res=response.body();
                    if(res.getToken()!=null){
                        Toast.makeText(LoginActivity.this, "Welcome "+res.getFirst_name(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,Dashboard.class);
                        preferences=getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                        editor=preferences.edit();
                        isLoggedIn=true;
                        //Url.Cookie=res.getToken();
                        editor.putString("token",res.getToken()).commit();
                        editor.putString("id",res.getId()).commit();
                        editor.putString("first_name",res.getFirst_name()).commit();
                        editor.putString("last_name",res.getLast_name()).commit();
                        editor.putString("email",res.getEmail()).commit();
                        editor.putString("gender",res.getGender()).commit();
                        editor.putString("batch",res.getBatch()).commit();
                        editor.putString("section",res.getSection()).commit();
                        editor.putString("user_name",res.getUser_name()).commit();
                        editor.putString("user_type",res.getUser_type()).commit();
                        editor.putString("user_image",res.getUser_image()).commit();
                        editor.putString("password",res.getPassword()).commit();
                        editor.putBoolean("isLoggedIn",isLoggedIn).commit();
                        startActivity(intent);
                        finish();
                        pd.dismiss();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, res.getMessage(), Toast.LENGTH_SHORT).show();
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 500 milliseconds
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            //Toast.makeText(LoginActivity.this, "Vibrating...", Toast.LENGTH_SHORT).show();
                            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            //deprecated in API 26
                            v.vibrate(1000);
                        }
                        pd.dismiss();
                    }

                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }
                pd.dismiss();
            }
        });

    }*/
}
