package com.example.collegeapp.BusinessLogics;

import com.example.collegeapp.API.CourseAPI;
import com.example.collegeapp.API.FeedbackAPI;
import com.example.collegeapp.Model.Course;
import com.example.collegeapp.Model.Feedback;
import com.example.collegeapp.Model.SubmissionResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.collegeapp.Url.Url.BASE_URL;

public class GetCourseLogic {
    CourseAPI courseAPI;
    boolean isSuccess=false;
    public static List<Course> courseList1;

    public void createInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        courseAPI=retrofit.create(CourseAPI.class);
    }

    /*public boolean getAllCourses(){
        createInstance();
        Call<List<Course>> listCall=courseAPI.getAllCourse();
        try{
            Response<List<Course>> registerResponseResponse=listCall.execute();
            if (registerResponseResponse.body()!=null){
                isSuccess=true;
            }
            else {
                isSuccess=false;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }*/

    public List<Course>  getAllCourses(){
        createInstance();
        Call<List<Course>> courseList = courseAPI.getAllCourse();

        try {
            Response<List<Course>> courseResponse= courseList.execute();

            if (courseResponse.isSuccessful()){
                courseList1=courseResponse.body();

                return courseList1;

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return courseList1;
    }


}
