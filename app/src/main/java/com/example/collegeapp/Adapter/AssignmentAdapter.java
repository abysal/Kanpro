package com.example.collegeapp.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.collegeapp.Activity.AssignmentDetails;
import com.example.collegeapp.Model.Assignment;
import com.example.collegeapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.AssignmentViewHolder> {
    List<Assignment> assignmentList;
    Context context;
    String deadline;

    public AssignmentAdapter(List<Assignment> assignmentList, Context context) {
        this.assignmentList = assignmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_assignment_row,viewGroup,false);
        return new AssignmentAdapter.AssignmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentAdapter.AssignmentViewHolder assignmentViewHolder, int i) {
        final Assignment assignment=assignmentList.get(i);
        assignmentViewHolder.module_code.setText("Module:"+assignment.getModule_code());
        String Deadline = assignment.getAssignment_deadline();
        SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault());
        SimpleDateFormat outputFormat=new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss",Locale.getDefault());
        try {
            deadline=outputFormat.format(inputFormat.parse(Deadline));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assignmentViewHolder.assignment_desc.setText("Deadline:"+deadline);

        assignmentViewHolder.btn_know_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AssignmentDetails.class);
                intent.putExtra("id",assignment.get_id());
                intent.putExtra("module_code",assignment.getModule_code());
                intent.putExtra("module_name",assignment.getModule_name());
                intent.putExtra("assignment_no",assignment.getAssignment_no());
                intent.putExtra("assignment_desc",assignment.getAssignment_desc());
                intent.putExtra("assignment_deadline",assignment.getAssignment_deadline());
                intent.putExtra("assignment_publish_date",assignment.getAssignment_publish_date());
                intent.putExtra("assignment_file",assignment.getAssignment_file());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public class AssignmentViewHolder extends RecyclerView.ViewHolder{
        public TextView module_code;
        public TextView assignment_desc;
        public Button btn_know_more;

        public AssignmentViewHolder(@NonNull View itemView) {
            super(itemView);
            module_code=itemView.findViewById(R.id.module_code);
            assignment_desc=itemView.findViewById(R.id.assignment_desc);
            btn_know_more=itemView.findViewById(R.id.btn_know_more);
        }

    }
}
