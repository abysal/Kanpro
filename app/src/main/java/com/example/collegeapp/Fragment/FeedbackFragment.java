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

import com.example.collegeapp.API.FeedbackAPI;
import com.example.collegeapp.Adapter.FeedbackAdapter;
import com.example.collegeapp.Model.FeedbackViewResponse;
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
public class FeedbackFragment extends Fragment {
    RecyclerView recyclerView;
   FeedbackAPI feedbackAPI;
    ActionBar actionBar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;



    public FeedbackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_feedback2, container, false);
        getActivity().setTitle("My Feedbacks");
        recyclerView=view.findViewById(R.id.feedback_recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        getMyFeedbacks();
        return  view;
    }

    public void getMyFeedbacks(){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Url.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        preferences=getActivity().getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
        String localUserId=preferences.getString("id","");
            feedbackAPI=retrofit.create(FeedbackAPI.class);
            Call<List<FeedbackViewResponse>> listCall=feedbackAPI.getMyFeedback(localUserId);
            listCall.enqueue(new Callback<List<FeedbackViewResponse>>() {
                @Override
                public void onResponse(Call<List<FeedbackViewResponse>> call, Response<List<FeedbackViewResponse>> response) {
                    if ((!response.isSuccessful())){
                        Toast.makeText(getActivity(), "Code"+response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }


                    List<FeedbackViewResponse> submissionViewResponseList=response.body();
                    recyclerView.setAdapter(new FeedbackAdapter(submissionViewResponseList,getActivity().getApplicationContext()));


                }

                @Override
                public void onFailure(Call<List<FeedbackViewResponse>> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
}
