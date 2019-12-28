package com.example.collegeapp.BusinessLogics;

import com.example.collegeapp.API.AssignmentAPI;
import com.example.collegeapp.API.CourseAPI;
import com.example.collegeapp.Model.Assignment;
import com.example.collegeapp.Model.Course;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.collegeapp.Url.Url.BASE_URL;

public class GetAssignmentLogic {
    AssignmentAPI assignmentAPI;
    boolean isSuccess=false;
    public static List<Assignment> assignmentList1;

    public void createInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        assignmentAPI=retrofit.create(AssignmentAPI.class);
    }

    public List<Assignment>  getAllAssignment(){
        createInstance();
        Call<List<Assignment>> assignmentList = assignmentAPI.getAllAssignment();

        try {
            Response<List<Assignment>> assignmentResponse= assignmentList.execute();

            if (assignmentResponse.isSuccessful()){
                assignmentList1=assignmentResponse.body();
                return assignmentList1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assignmentList1;
    }

}
