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

import com.example.collegeapp.Activity.NoticeDetails;
import com.example.collegeapp.Model.Notice;
import com.example.collegeapp.R;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
    List<Notice> noticeList;
    Context context;

    public NoticeAdapter(List<Notice> noticeList, Context context) {
        this.noticeList = noticeList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_notice_row,viewGroup,false);
        return new NoticeAdapter.NoticeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder noticeViewHolder, int i) {
        final Notice notice=noticeList.get(i);
        noticeViewHolder.notice_title.setText(notice.getNotice_title());
        noticeViewHolder.notice_subject.setText(notice.getNotice_subject());
       // String url=Url.BASE_URL;
        //Picasso.get().load(url+course.getCourse_image()).into(courseViewHolder.sample_image);

        noticeViewHolder.btn_know_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, NoticeDetails.class);
                intent.putExtra("id",notice.getId());
                intent.putExtra("notice_title",notice.getNotice_title());
                intent.putExtra("notice_subject",notice.getNotice_subject());
                intent.putExtra("notice_desc",notice.getNotice_desc());
                intent.putExtra("notice_date",notice.getNotice_date());
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder{
        public TextView notice_title;
        public TextView notice_subject;
        public Button btn_know_more;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            notice_title=itemView.findViewById(R.id.notice_title);
            notice_subject=itemView.findViewById(R.id.notice_subject);
            btn_know_more=itemView.findViewById(R.id.btn_know_more);
        }

    }
}
