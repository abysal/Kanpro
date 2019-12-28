package com.example.collegeapp.BusinessLogics;

import com.example.collegeapp.API.UserAPI;
import com.example.collegeapp.Model.RegisterResponse;
import com.example.collegeapp.Model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.collegeapp.Url.Url.BASE_URL;

public class RegisterLogic {
    private String first_name,last_name,email,batch,section,gender,user_image,user_name,password;;
    boolean isSuccess=false;
    UserAPI userAPI;
    public static String message_success_reg,message_error_reg,message_reg;

    public RegisterLogic(String first_name, String last_name, String email, String batch, String section, String gender, String user_image, String user_name, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.batch = batch;
        this.section = section;
        this.gender = gender;
        this.user_image = user_image;
        this.user_name = user_name;
        this.password = password;
    }

    public void createInstance(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userAPI=retrofit.create(UserAPI.class);
    }
    public boolean addUser(){
        createInstance();
        User user=new User(first_name,last_name,email,batch,section,gender,"",user_name,password);
        Call<RegisterResponse> userCall=userAPI.addUser(user);
        try{
            Response<RegisterResponse> registerResponseResponse=userCall.execute();
            if (registerResponseResponse.body().getMessage_success()!=null){
                isSuccess=true;
                message_success_reg=registerResponseResponse.body().getMessage_success();
            }
            else if(registerResponseResponse.body().getMessage_error()!=null || registerResponseResponse.body().getMessage()!=null){
                isSuccess=false;
                message_error_reg=registerResponseResponse.body().getMessage_error();
                message_reg=registerResponseResponse.body().getMessage();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
