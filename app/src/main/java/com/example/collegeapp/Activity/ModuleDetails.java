package com.example.collegeapp.Activity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.API.ModuleAPI;
import com.example.collegeapp.Model.Module;
import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModuleDetails extends AppCompatActivity {
    ActionBar actionBar;
    TextView module_code,module_name,topic1,topic2,topic3,topic4,topic5,topic6,topic7,topic8,topic9,topic10,topic11,topic12;
    TextView topic13,topic14,topic15,topic16,topic17,topic18,topic19,topic20;
    String id,module_code_str,module_name_str,topic1_str,topic2_str,topic3_str,topic4_str,topic5_str,topic6_str,topic7_str,topic8_str,topic9_str
            ,topic10_str,topic11_str,topic12_str,topic13_str,topic14_str,topic15_str,topic16_str,topic17_str,topic18_str,topic19_str,
            topic20_str;
    String fullPath;
    long downloadID;
    ModuleAPI moduleAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_details);

        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Module Detail");
        initViews();
        getContent();

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null) {
            module_code_str=bundle.getString("module_code");
            module_name_str=bundle.getString("module_name");
            module_code.setText(module_code_str);
            module_name.setText(module_name_str);
            id=bundle.getString("mid");
            //Toast.makeText(this, id, Toast.LENGTH_SHORT).show();


        }
        }

    public void initViews() {
        module_code = (TextView) findViewById(R.id.module_code);
        module_name = (TextView) findViewById(R.id.module_name);
        topic1 = (TextView) findViewById(R.id.topic1);
        topic2 = (TextView) findViewById(R.id.topic2);
        topic3 = (TextView) findViewById(R.id.topic3);
        topic4 = (TextView) findViewById(R.id.topic4);
        topic5 = (TextView) findViewById(R.id.topic5);
        topic6 = (TextView) findViewById(R.id.topic6);
        topic7 = (TextView) findViewById(R.id.topic7);
        topic8 = (TextView) findViewById(R.id.topic8);
        topic9 = (TextView) findViewById(R.id.topic9);
        topic10 = (TextView) findViewById(R.id.topic10);
        topic11 = (TextView) findViewById(R.id.topic11);
        topic12 = (TextView) findViewById(R.id.topic12);
        topic13 = (TextView) findViewById(R.id.topic13);
        topic14 = (TextView) findViewById(R.id.topic14);
        topic15 = (TextView) findViewById(R.id.topic15);
        topic16 = (TextView) findViewById(R.id.topic16);
        topic17 = (TextView) findViewById(R.id.topic17);
        topic18 = (TextView) findViewById(R.id.topic18);
        topic19 = (TextView) findViewById(R.id.topic19);
        topic20 = (TextView) findViewById(R.id.topic20);
    }

    public void getContent(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Bundle bundle=getIntent().getExtras();
        String mid=bundle.getString("mid");
        moduleAPI=retrofit.create(ModuleAPI.class);
        Call<Module> listCall=moduleAPI.getSingleModuleContent(mid);


        listCall.enqueue(new Callback<Module>() {
            @Override
            public void onResponse(Call<Module> call, Response<Module> response) {
                if ((!response.isSuccessful())){
                    Toast.makeText(ModuleDetails.this, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    topic1_str=response.body().getTopic1();
                    if (!topic1_str.equals("")){
                        topic1.setText(topic1_str);
                        final String fullPath1= Url.BASE_URL+topic1_str;
                        topic1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath1));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic2_str=response.body().getTopic2();
                    if (!topic2_str.equals("")){
                        topic2.setText(topic2_str);
                        final String fullPath2= Url.BASE_URL+topic2_str;
                        topic2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath2));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    topic3_str=response.body().getTopic3();
                    if (!topic3_str.equals("")){
                        topic3.setText(topic3_str);
                        final String fullPath3= Url.BASE_URL+topic3_str;
                        topic3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath3));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic4_str=response.body().getTopic4();
                    if (!topic4_str.equals("")){
                        topic4.setText(topic4_str);
                        final String fullPath4= Url.BASE_URL+topic4_str;
                        topic4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath4));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    topic5_str=response.body().getTopic5();
                    if (!topic5_str.equals("")){
                        topic5.setText(topic5_str);
                        final String fullPath5= Url.BASE_URL+topic5_str;
                        topic5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath5));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic6_str=response.body().getTopic6();
                    if (!topic6_str.equals("")){
                        topic6.setText(topic6_str);
                        final String fullPath6= Url.BASE_URL+topic6_str;
                        topic6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath6));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    topic7_str=response.body().getTopic7();
                    if (!topic7_str.equals("")){
                        topic7.setText(topic7_str);
                        final String fullPath7= Url.BASE_URL+topic7_str;
                        topic7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath7));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic8_str=response.body().getTopic8();
                    if (!topic8_str.equals("")){
                        topic8.setText(topic7_str);
                        final String fullPath8= Url.BASE_URL+topic8_str;
                        topic8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath8));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic9_str=response.body().getTopic9();
                    if (!topic9_str.equals("")){
                        topic9.setText(topic9_str);
                        final String fullPath9= Url.BASE_URL+topic9_str;
                        topic9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath9));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic10_str=response.body().getTopic10();
                    if (!topic10_str.equals("")){
                        topic10.setText(topic10_str);
                        final String fullPath10= Url.BASE_URL+topic10_str;
                        topic10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath10));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic11_str=response.body().getTopic11();
                    if (!topic11_str.equals("")){
                        topic11.setText(topic11_str);
                        final String fullPath11= Url.BASE_URL+topic11_str;
                        topic11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath11));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic12_str=response.body().getTopic12();
                    if (!topic12_str.equals("")){
                        topic12.setText(topic12_str);
                        final String fullPath12= Url.BASE_URL+topic12_str;
                        topic12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath12));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic13_str=response.body().getTopic13();
                    if (!topic13_str.equals("")){
                        topic13.setText(topic13_str);
                        final String fullPath13= Url.BASE_URL+topic13_str;
                        topic13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath13));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic14_str=response.body().getTopic14();
                    if (!topic14_str.equals("")){
                        topic14.setText(topic14_str);
                        final String fullPath14= Url.BASE_URL+topic14_str;
                        topic14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath14));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic15_str=response.body().getTopic15();
                    if (!topic15_str.equals("")){
                        topic15.setText(topic15_str);
                        final String fullPath15= Url.BASE_URL+topic15_str;
                        topic15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath15));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic16_str=response.body().getTopic16();
                    if (!topic16_str.equals("")){
                        topic16.setText(topic16_str);
                        final String fullPath16= Url.BASE_URL+topic16_str;
                        topic16.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath16));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic17_str=response.body().getTopic17();
                    if (!topic17_str.equals("")){
                        topic17.setText(topic17_str);
                        final String fullPath17= Url.BASE_URL+topic17_str;
                        topic17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath17));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic18_str=response.body().getTopic18();
                    if (!topic18_str.equals("")){
                        topic18.setText(topic18_str);
                        final String fullPath18= Url.BASE_URL+topic18_str;
                        topic18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath18));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic19_str=response.body().getTopic19();
                    if (!topic19_str.equals("")){
                        topic19.setText(topic19_str);
                        final String fullPath19= Url.BASE_URL+topic19_str;
                        topic19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath19));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    topic20_str=response.body().getTopic20();
                    if (!topic20_str.equals("")){
                        topic20.setText(topic20_str);
                        final String fullPath20= Url.BASE_URL+topic20_str;
                        topic20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                                DownloadManager.Request mRqRequest = new DownloadManager.Request(
                                        Uri.parse(fullPath20));
                                mRqRequest.setDescription("Downloading File");
                                //mRqRequest.setTitle("Lecture File");
                                downloadID=mManager.enqueue(mRqRequest);
                                Toast.makeText(ModuleDetails.this, "Downloading", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }



            }

            @Override
            public void onFailure(Call<Module> call, Throwable t) {
                Toast.makeText(ModuleDetails.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
