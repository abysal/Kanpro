package com.example.collegeapp.Fragment;


import android.content.Context;
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

import com.example.collegeapp.API.SubmissionAPI;
import com.example.collegeapp.Adapter.SubmissionAdapter2;
import com.example.collegeapp.Model.SubmissionViewResponse;
import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Submission extends Fragment {
    RecyclerView recyclerView;
    SubmissionAPI submissionAPI;
    ActionBar actionBar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;



    public Submission() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view=inflater.inflate(R.layout.fragment_submission2, container, false);
        getActivity().setTitle("My Submissions");
        recyclerView=view.findViewById(R.id.submission_recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        getMySubmissions();
        return  view;
    }
    public void getMySubmissions(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        preferences=getActivity().getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
        String localUserId=preferences.getString("id","");
        submissionAPI=retrofit.create(SubmissionAPI.class);
        Call<List<SubmissionViewResponse>> listCall=submissionAPI.getMySubmissions(localUserId);
        listCall.enqueue(new Callback<List<SubmissionViewResponse>>() {
            @Override
            public void onResponse(Call<List<SubmissionViewResponse>> call, Response<List<SubmissionViewResponse>> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(getActivity(), "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }


                List<SubmissionViewResponse> submissionViewResponseList=response.body();
                recyclerView.setAdapter(new SubmissionAdapter2(submissionViewResponseList,getActivity().getApplicationContext()));


            }

            @Override
            public void onFailure(Call<List<SubmissionViewResponse>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
