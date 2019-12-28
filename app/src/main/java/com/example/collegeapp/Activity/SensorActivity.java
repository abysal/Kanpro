package com.example.collegeapp.Activity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.collegeapp.R;

import java.util.List;

public class SensorActivity extends AppCompatActivity {
    SensorManager sensorManager;
    TextView gyroscope_value,proximity_value,light_value,humidity_value;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Sensors");
        gyroscope_value=(TextView)findViewById(R.id.gyroscope_value);
        proximity_value=(TextView)findViewById(R.id.proximity_value);
        light_value=(TextView)findViewById(R.id.light_value);
        humidity_value=(TextView)findViewById(R.id.humidity_value);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> devicesensor=sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s:devicesensor){
            Log.d("SensorName:",s.getName());
        }
        gyroscopeInstance();
       proximityInstance();
        lightInstance();
        humidityInstance();
    }

    private void gyroscopeInstance(){
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            SensorEventListener gyrolistener=new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.values[0] <= 0.5f ) {
                        //Toast.makeText(SensorActivity.this, "left", Toast.LENGTH_SHORT).show();
                        gyroscope_value.setText("Left");
                    } else if(event.values[0]>-0.5f) {
                        //Toast.makeText(SensorActivity.this, "right", Toast.LENGTH_SHORT).show();
                        gyroscope_value.setText("Right");
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    //Toast.makeText(SensorActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            };
        if (sensor == null) {
            gyroscope_value.setText("No Gyroscope Sensor!");
        }
        else{

            sensorManager.registerListener(gyrolistener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

     private void proximityInstance(){
       sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
       Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
          final WindowManager.LayoutParams params = this.getWindow().getAttributes();
             SensorEventListener proxilistener = new SensorEventListener() {
                 @Override
                 public void onSensorChanged(SensorEvent event) {
                     if (event.values[0] >= -4 && event.values[0] <= 4) {
                         //Toast.makeText(SensorActivity.this, "near", Toast.LENGTH_SHORT).show();
                         proximity_value.setText("Near");
                     } else {
                         //Toast.makeText(SensorActivity.this, "far", Toast.LENGTH_SHORT).show();
                         proximity_value.setText("Far");
                     }
                     if(event.values[0]<=10){
                         params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                         params.screenBrightness = 0;
                         getWindow().setAttributes(params);
                     }
                     else{
                         params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                         params.screenBrightness = -1f;
                         getWindow().setAttributes(params);
                     }
                 }

                 @Override
                 public void onAccuracyChanged(Sensor sensor, int accuracy) {
                     //Toast.makeText(SensorActivity.this, "", Toast.LENGTH_SHORT).show();
                 }
             };
         if (sensor == null) {
             proximity_value.setText("No Proximity Sensor!");
         }
         else {
             sensorManager.registerListener(proxilistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
         }
   }
    private void lightInstance(){
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            SensorEventListener lightlistener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                    if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                        // Toast.makeText(SensorActivity.this, "light"+event.values[0], Toast.LENGTH_SHORT).show();
                        light_value.setText(event.values[0] + "");
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    // Toast.makeText(SensorActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            };
        if (sensor == null) {
            light_value.setText("No Light Sensor!");
        }
        else {
            sensorManager.registerListener(lightlistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    private void humidityInstance(){
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        SensorEventListener lightlistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
                    // Toast.makeText(SensorActivity.this, "light"+event.values[0], Toast.LENGTH_SHORT).show();
                    humidity_value.setText(event.values[0] + "%");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                // Toast.makeText(SensorActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        };
        if (sensor == null) {
            humidity_value.setText("No Humidity Sensor!");
        }
        else {
            sensorManager.registerListener(lightlistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
