package com.example.collegeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collegeapp.Activity.CourseDetails;
import com.example.collegeapp.Model.Course;
import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.CourseViewHolder> {
    List<Course> courseList;
    Context context;

    public CourseViewAdapter(List<Course> courseList, Context context) {
        this.courseList = courseList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_course_row,viewGroup,false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder courseViewHolder, int i) {
        final Course course=courseList.get(i);
        courseViewHolder.sample_heading.setText(course.getCourse_name());
        String url= Url.BASE_URL;
        Picasso.get().load(url+course.getCourse_image()).into(courseViewHolder.sample_image);

        courseViewHolder.sample_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CourseDetails.class);
                intent.putExtra("id",course.getId());
                intent.putExtra("course_name",course.getCourse_name());
                intent.putExtra("course_price","Rs:"+course.getCourse_price());
                intent.putExtra("course_duration",course.getCourse_duration());
                intent.putExtra("course_desc",course.getCourse_desc());
                intent.putExtra("course_modules",course.getCourse_modules());
                intent.putExtra("course_image",course.getCourse_image());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }


    public class CourseViewHolder extends RecyclerView.ViewHolder{
        public TextView sample_heading;
        public ImageView sample_image;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            sample_heading=itemView.findViewById(R.id.course_name);
            sample_image=itemView.findViewById(R.id.course_image);
        }

    }



}
