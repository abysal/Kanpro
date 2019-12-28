package com.example.collegeapp.API;

import com.example.collegeapp.Model.Module;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ModuleAPI {
    @GET("modules")
    Call<List<Module>> getAllModules();

    @GET("modules/{id}")
    Call<Module> getSingleModuleContent(@Path("id") String id);

}
