package com.example.collegeapp.Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.collegeapp.API.CourseAPI;
import com.example.collegeapp.Adapter.CourseViewAdapter;
import com.example.collegeapp.BusinessLogics.GetCourseLogic;
import com.example.collegeapp.Model.Course;
import com.example.collegeapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoursesFragment extends Fragment {
    RecyclerView recyclerView;
    CourseAPI courseAPI;
    ActionBar actionBar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public CoursesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_courses, container, false);
        getActivity().setTitle("Courses");
       recyclerView=view.findViewById(R.id.courses_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        getAllCourses();
        return view;
    }
    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
    public void getAllCourses(){
        GetCourseLogic getCourseLogic=new GetCourseLogic();
        StrictMode();
        if(getCourseLogic.getAllCourses()!=null){
            List<Course> courseList=getCourseLogic.getAllCourses();
            recyclerView.setAdapter(new CourseViewAdapter(courseList,getActivity().getApplicationContext()));
        }
        else{
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }

      /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        courseAPI=retrofit.create(CourseAPI.class);
        Call<List<Course>> listCall=courseAPI.getAllCourse();

        listCall.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(getActivity(), "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Course> courseList=response.body();
                recyclerView.setAdapter(new CourseViewAdapter(courseList,getActivity().getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }



}
