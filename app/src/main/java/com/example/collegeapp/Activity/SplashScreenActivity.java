package com.example.collegeapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.collegeapp.R;

public class SplashScreenActivity extends AppCompatActivity {
    SharedPreferences preferences;
    Boolean checkLogin;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        actionBar=getSupportActionBar();
        actionBar.setTitle("College App");
        actionBar.setSubtitle("Welcome");
        preferences=getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
        checkLogin=preferences.getBoolean("isLoggedIn",false);
        if (checkLogin==true){
            final Intent intent=new Intent(SplashScreenActivity.this, Dashboard.class);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            },2000);
        }
        else if (checkLogin==false){
            final Intent intent=new Intent(SplashScreenActivity.this,LoginActivity.class);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(intent);
                    finish();
                }
            },2000);
        }
    }
}
