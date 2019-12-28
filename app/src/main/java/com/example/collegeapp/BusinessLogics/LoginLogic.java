package com.example.collegeapp.BusinessLogics;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.collegeapp.API.UserAPI;
import com.example.collegeapp.Model.LoginResponse;
import com.example.collegeapp.Model.User;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.collegeapp.Url.Url.BASE_URL;

public class LoginLogic {
    private String user_name;
    private String password;
    boolean isSuccess=false;
    UserAPI userAPI;
    Context context;
    public static String _id_l,first_name_l,last_name_l,batch_l,gender_l,section_l,user_name_l,email_l,user_type_l,
            user_image_l,password_l,token_l,message_l,err_message_l;

    public LoginLogic(String user_name, String password) {
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


    public boolean checkUser(){
        createInstance();
        User user=new User("","","","","","","",user_name,password);
        Call<LoginResponse> userCall=userAPI.getUser(user);
        try{
            Response<LoginResponse> loginResponseResponse=userCall.execute();
            if (loginResponseResponse.body().getToken()!=null && loginResponseResponse.body().getMessage()==null){
                isSuccess=true;
                _id_l=loginResponseResponse.body().getId();
                first_name_l=loginResponseResponse.body().getFirst_name();
                last_name_l=loginResponseResponse.body().getLast_name();
                email_l=loginResponseResponse.body().getEmail();
                batch_l=loginResponseResponse.body().getBatch();
                section_l=loginResponseResponse.body().getSection();
                gender_l=loginResponseResponse.body().getGender();
                user_image_l=loginResponseResponse.body().getUser_image();
                user_name_l=loginResponseResponse.body().getUser_name();
                user_type_l=loginResponseResponse.body().getUser_type();
                password_l=loginResponseResponse.body().getPassword();
                token_l=loginResponseResponse.body().getToken();

            }
            else if (loginResponseResponse.body().getMessage()!=null){
                message_l=loginResponseResponse.body().getMessage();
                isSuccess=false;

            }
            else if(loginResponseResponse==null){
                isSuccess=false;

            }
        }
        catch (IOException e){
            e.printStackTrace();

        }
    return isSuccess;
    }

}
