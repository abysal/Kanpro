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

import com.example.collegeapp.API.NoticeAPI;
import com.example.collegeapp.Adapter.NoticeAdapter;
import com.example.collegeapp.Model.Notice;
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
public class NoticeFragment extends Fragment {
    RecyclerView recyclerView;
    NoticeAPI noticeAPI;
    ActionBar actionBar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;



    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notice_fragmente, container, false);
        getActivity().setTitle("Notices");
        recyclerView=view.findViewById(R.id.courses_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        getAllNotices();
        return view;
    }

    public void getAllNotices(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        noticeAPI=retrofit.create(NoticeAPI.class);
        Call<List<Notice>> listCall=noticeAPI.getAllNotice();

        listCall.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(getActivity(), "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Notice> noticeList=response.body();
                recyclerView.setAdapter(new NoticeAdapter(noticeList,getActivity().getApplicationContext()));

            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
