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

import com.example.collegeapp.Activity.ModuleDetails;
import com.example.collegeapp.Model.Module;
import com.example.collegeapp.R;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
    List<Module> moduleList;
    Context context;

    public ModuleAdapter(List<Module> moduleList, Context context) {
        this.moduleList = moduleList;
        this.context = context;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_module_row,viewGroup,false);
        return new ModuleAdapter.ModuleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleAdapter.ModuleViewHolder moduleViewHolder, int i) {
        final Module module=moduleList.get(i);
        moduleViewHolder.module_code.setText("Module Code:"+module.getModule_code());
        moduleViewHolder.module_name.setText("Module Name:"+module.getModule_name());

        moduleViewHolder.btn_view_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ModuleDetails.class);
                intent.putExtra("mid",module.get_id());
                intent.putExtra("module_code",module.getModule_code());
                intent.putExtra("module_name",module.getModule_name());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }

    public class ModuleViewHolder extends RecyclerView.ViewHolder{
        public TextView module_code;
        public TextView module_name;
        public Button btn_view_content;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            module_code=itemView.findViewById(R.id.module_code);
            module_name=itemView.findViewById(R.id.module_name);
            btn_view_content=itemView.findViewById(R.id.btn_view_content);
        }

    }
}
