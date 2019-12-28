package com.example.collegeapp.Fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.collegeapp.API.ModuleAPI;
import com.example.collegeapp.Adapter.ModuleAdapter;
import com.example.collegeapp.Model.Module;
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
public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ModuleAPI moduleAPI;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Home");
        recyclerView=view.findViewById(R.id.modules_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        getAllModules();
        return view;
    }

    public void getAllModules(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        moduleAPI=retrofit.create(ModuleAPI.class);
        Call<List<Module>> listCall=moduleAPI.getAllModules();

        listCall.enqueue(new Callback<List<Module>>() {
            @Override
            public void onResponse(Call<List<Module>> call, Response<List<Module>> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(getActivity(), "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Module> moduleList=response.body();
                recyclerView.setAdapter(new ModuleAdapter(moduleList,getActivity().getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Module>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
