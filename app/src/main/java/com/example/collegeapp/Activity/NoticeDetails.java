package com.example.collegeapp.Activity;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.collegeapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoticeDetails extends AppCompatActivity {
    ActionBar actionBar;
    TextView notice_title,notice_subject,notice_desc,notice_date;
    Button btn_knowMore;
    String pdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);

        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Notice Detail");

        notice_title=(TextView)findViewById(R.id.notice_title);
        notice_subject=(TextView)findViewById(R.id.notice_subject);
        notice_desc=(TextView)findViewById(R.id.notice_desc);
        notice_date=(TextView) findViewById(R.id.notice_date);
        btn_knowMore=(Button)findViewById(R.id.btn_knowMore) ;

        btn_knowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"+9779843624525"));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            notice_title.setText(bundle.getString("notice_title"));
            notice_subject.setText(bundle.getString("notice_subject"));
            notice_desc.setText(bundle.getString("notice_desc"));
            String ntcdate=bundle.getString("notice_date");
            SimpleDateFormat inputFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault());
            SimpleDateFormat outputFormat=new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss",Locale.getDefault());
            try {
                pdate=outputFormat.format(inputFormat.parse(ntcdate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            notice_date.setText(pdate);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
