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

import com.example.collegeapp.API.AssignmentAPI;
import com.example.collegeapp.Adapter.AssignmentAdapter;
import com.example.collegeapp.BusinessLogics.GetAssignmentLogic;
import com.example.collegeapp.Model.Assignment;
import com.example.collegeapp.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentFragment extends Fragment {
    RecyclerView recyclerView;
   AssignmentAPI assignmentAPI;
    ActionBar actionBar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public AssignmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_assignment, container, false);
        getActivity().setTitle("Assignments");
        recyclerView=view.findViewById(R.id.assignment_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        getAllAssignments();
        return view;
    }

    private void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
    public void getAllAssignments() {
        GetAssignmentLogic getAssignmentLogic=new GetAssignmentLogic();
        StrictMode();
        if (getAssignmentLogic.getAllAssignment() != null) {
            List<Assignment> assignmentList = getAssignmentLogic.getAllAssignment();
            recyclerView.setAdapter(new AssignmentAdapter(assignmentList, getActivity().getApplicationContext()));
        } else {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

   /* public void getAllAssignments(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        assignmentAPI=retrofit.create(AssignmentAPI.class);
        Call<List<Assignment>> listCall=assignmentAPI.getAllAssignment();
        listCall.enqueue(new Callback<List<Assignment>>() {
            @Override
            public void onResponse(Call<List<Assignment>> call, Response<List<Assignment>> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(getActivity(), "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Assignment> assignmentList=response.body();
                recyclerView.setAdapter(new AssignmentAdapter(assignmentList,getActivity().getApplicationContext()));


            }

            @Override
            public void onFailure(Call<List<Assignment>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/

}
