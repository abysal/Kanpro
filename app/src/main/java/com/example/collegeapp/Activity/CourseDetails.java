package com.example.collegeapp.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;
import com.squareup.picasso.Picasso;

public class CourseDetails extends AppCompatActivity {
    ActionBar actionBar;
    TextView course_name,course_price,course_duration,course_modules,course_desc;
    ImageView course_image;
    Button btn_enquire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Course Detail");

        course_name=(TextView)findViewById(R.id.course_name);
        course_price=(TextView)findViewById(R.id.course_price);
        course_duration=(TextView)findViewById(R.id.course_duration);
        course_image=(ImageView)findViewById(R.id.course_image);
        course_modules=(TextView)findViewById(R.id.course_modules);
        course_desc=(TextView)findViewById(R.id.course_desc);
        btn_enquire=(Button)findViewById(R.id.btn_enquire);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            course_name.setText(bundle.getString("course_name"));
            course_price.setText(bundle.getString("course_price"));
            course_duration.setText(bundle.getString("course_duration"));
            String url= Url.BASE_URL;
            String image=bundle.getString("course_image");
            String fullPath=url+image;
            Picasso.get().load(fullPath).into(course_image);

            course_modules.setText(bundle.getString("course_modules"));
            course_desc.setText(bundle.getString("course_desc"));
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
