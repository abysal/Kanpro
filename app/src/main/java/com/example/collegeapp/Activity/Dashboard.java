package com.example.collegeapp.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.Broadcast.ConnectionBroadcastReceiver;
import com.example.collegeapp.Broadcast.CreateChannel;
import com.example.collegeapp.Fragment.FeedbackFragment;
import com.example.collegeapp.Fragment.Submission;
import com.example.collegeapp.Fragment.AssignmentFragment;
import com.example.collegeapp.Fragment.CoursesFragment;
import com.example.collegeapp.Fragment.HomeFragment;
import com.example.collegeapp.Fragment.NoticeFragment;
import com.example.collegeapp.Fragment.UserProfile;
import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;
import com.squareup.picasso.Picasso;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout fragmentContainer;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Boolean isLoggedIn;
    ImageView user_image;
    TextView user_full_name;
    TextView user_email;
    SensorManager sensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    ConnectionBroadcastReceiver connectionBroadcastReceiver=new ConnectionBroadcastReceiver(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CreateChannel channel=new CreateChannel(this);
        channel.createChannel();

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        View v=navigationView.getHeaderView(0);
        user_image=(ImageView)v.findViewById(R.id.user_image);
        user_full_name=(TextView)v.findViewById(R.id.user_full_name);
        user_email=(TextView)v.findViewById(R.id.user_email);

        preferences=getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
        String first_name=preferences.getString("first_name","");
        String last_name=preferences.getString("last_name","");
        String fullname=first_name+" "+last_name;
        String email=preferences.getString("email","");
        String image=preferences.getString("user_image","");
        String fullpath= Url.BASE_URL +image;
        Picasso.get().load(fullpath).into(user_image);
        user_full_name.setText(fullname);
        user_email.setText(email);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        fragmentContainer=findViewById(R.id.fragment_container);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.fragment_container,new HomeFragment());
        transaction.commit();
        proximityInstance();
        shake();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_feedback) {
            Intent intent=new Intent(Dashboard.this,FeedbackActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if (id == R.id.action_sensor) {
            Intent intent=new Intent(Dashboard.this,SensorActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            return true;
        }
        if (id == R.id.action_help) {
            return true;
        }
        if (id==R.id.action_logout){
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("College App")
                    .setMessage("Are you sure want to logout?")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Dashboard.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            preferences=getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                            editor=preferences.edit();
                            isLoggedIn = false;
                            editor.putBoolean("isLoggedIn", isLoggedIn).commit();
                            startActivity(intent);
                            //do not start activity if you want to exit the app.
                            finish();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            builder.show();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();

        if (id == R.id.nav_home) {
            Intent intent =new Intent(Dashboard.this,Dashboard.class);
            startActivity(intent);

        } else if (id == R.id.nav_course) {
            transaction.replace(R.id.fragment_container,new CoursesFragment());
            transaction.commit();

        } else if (id == R.id.nav_assignment) {
            transaction.replace(R.id.fragment_container,new AssignmentFragment());
            transaction.commit();

        }
        else if (id == R.id.nav_notice) {
            transaction.replace(R.id.fragment_container,new NoticeFragment());
            transaction.commit();

        }
        else if (id == R.id.nav_profile) {
            transaction.replace(R.id.fragment_container,new UserProfile());
            transaction.commit();

        }
        else if (id == R.id.nav_submission) {
            transaction.replace(R.id.fragment_container,new Submission());
            transaction.commit();
        }
        else if (id == R.id.nav_feedback) {
            transaction.replace(R.id.fragment_container,new FeedbackFragment());
            transaction.commit();
        }
        else if(id==R.id.nav_phone){
            Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"+9779843624525"));
            startActivity(intent);
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(connectionBroadcastReceiver);
    }

    private void shake(){
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener accellistener = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values=event.values;
                float xaxis=values[0];
                float yaxis=values[1];
                float zaxis=values[2];
                mAccelLast=mAccelCurrent;
                mAccelCurrent=(float)Math.sqrt((double) (xaxis*xaxis+yaxis*yaxis+zaxis*zaxis));
                float delta=mAccelCurrent-mAccelLast;
                mAccel=mAccel*0.9f+delta;
                if(mAccel>12) {
                    /*finish();
                    startActivity(getIntent());*/
                    Intent intent = new Intent(Dashboard.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    preferences = getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                    editor = preferences.edit();
                    isLoggedIn = false;
                    editor.putBoolean("isLoggedIn", isLoggedIn).commit();
                    startActivity(intent);
                    //do not start activity if you want to exit the app.
                    finish();

                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
        if (sensor == null) {
            Toast.makeText(this, "No sensor", Toast.LENGTH_SHORT).show();
        }
        else {
            sensorManager.registerListener(accellistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    private void proximityInstance(){
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener proxilistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0]==0) {
                                    Intent intent = new Intent(Dashboard.this, LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    preferences=getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                                    editor=preferences.edit();
                                    isLoggedIn = false;
                                    editor.putBoolean("isLoggedIn", isLoggedIn).commit();
                                    startActivity(intent);
                                    //do not start activity if you want to exit the app.
                                    finish();

                } else {
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //Toast.makeText(SensorActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        };
        if (sensor == null) {
            Toast.makeText(this, "No sensor", Toast.LENGTH_SHORT).show();
        }
        else {
            sensorManager.registerListener(proxilistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
