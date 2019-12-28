package com.example.collegeapp.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.OpenableColumns;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.collegeapp.API.SubmissionAPI;
import com.example.collegeapp.Model.Submission;
import com.example.collegeapp.Model.SubmissionResponse;
import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitAssignment extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener,
        OnPageErrorListener {
    EditText et_assignment_title,et_assignment_links;
    Button btn_browse_assignment_file,btn_submit_assignment;
    ActionBar actionBar;
    SubmissionAPI submissionAPI;
    private int pageNumber = 0;
    

    private String pdfFileName;
    private PDFView pdfView;
    public static final int FILE_PICKER_REQUEST_CODE = 1;
    private String pdfPath;
    String user_file,assign_id;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_assignment);
        actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Submit Assignment");
        et_assignment_title=(EditText)findViewById(R.id.et_assignment_title);
        et_assignment_links=(EditText)findViewById(R.id.et_assignment_links);
        btn_browse_assignment_file=(Button)findViewById(R.id.btn_browse_assignment_file);
        btn_submit_assignment=(Button)findViewById(R.id.btn_submit_assignment);
        pdfView = (PDFView) findViewById(R.id.pdfView);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            assign_id=bundle.getString("id");
            //Log.d("AssignID",assign_id);
        }

        btn_browse_assignment_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchPicker();
            }
        });

        btn_submit_assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    uploadSubmission();
                }

            }
        });
    }
    private void launchPicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(FILE_PICKER_REQUEST_CODE)
                .withHiddenFiles(true)
                .withFilter(Pattern.compile(".*\\.pdf$"))
                .withTitle("Select PDF file")
                .start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            File file = new File(path);
            displayFromFile(file);
            if (path != null) {
                Log.d("Path: ", path);
                pdfPath = path;
                Toast.makeText(this, "Picked file: " + path, Toast.LENGTH_LONG).show();
            }
        }

    }
    private void displayFromFile(File file) {

        Uri uri = Uri.fromFile(new File(file.getAbsolutePath()));
        pdfFileName = getFileName(uri);

        pdfView.fromFile(file)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .onPageError(this)
                .load();
    }
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }



    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();

        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            //Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    @Override
    public void onPageError(int page, Throwable t) {

    }
    public void createInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        submissionAPI = retrofit.create(SubmissionAPI.class);
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    private void uploadFile() {
        if (pdfPath == null) {
            Toast.makeText(this, "Please select a file ", Toast.LENGTH_LONG).show();
            return;
        } else {
            File file = new File(pdfPath);
            // Parsing any Media type file
            RequestBody rb=RequestBody.create(MediaType.parse("application/pdf"),file);
            MultipartBody.Part submission=MultipartBody.Part.createFormData("assignment_file_user",file.getName(),rb);

            createInstance();
            Call<SubmissionResponse> call = submissionAPI.uploadFile(submission);
            StrictMode();
            try{
                Response<SubmissionResponse> submissionResponseResponse=call.execute();
                user_file="uploads\\"+submissionResponseResponse.body().getUser_file();

            }
            catch (Exception e){
                Toast.makeText(SubmitAssignment.this, "Error", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }
    private void uploadSubmission(){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Uploading File...");
        pd.setCancelable(false);
        pd.show();
        if (pdfPath!=null){
        uploadFile();
        preferences=this.getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
        String id1=preferences.getString("id","");
        createInstance();

        Submission submission=new Submission(id1,assign_id,et_assignment_title.getText().toString(),
                et_assignment_links.getText().toString(),user_file);
        Call<SubmissionResponse> submissionResponseCall=submissionAPI.submitAssignment(submission);
        submissionResponseCall.enqueue(new Callback<SubmissionResponse>() {
            @Override
            public void onResponse(Call<SubmissionResponse> call, Response<SubmissionResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(SubmitAssignment.this, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    return;
                }
                else if (response.body().getSuc_message()!=null){
                    Toast.makeText(SubmitAssignment.this, response.body().getSuc_message(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SubmitAssignment.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                    pd.dismiss();
                }
                else if (response.body().getErr_message()!=null){
                    Toast.makeText(SubmitAssignment.this, response.body().getErr_message(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SubmissionResponse> call, Throwable t) {
                Toast.makeText(SubmitAssignment.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
        }
        else{
            Toast.makeText(this, "Please select a submission file", Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }

    }
    private boolean validate() {
        if (TextUtils.isEmpty(et_assignment_title.getText().toString())) {
            et_assignment_title.setError("Enter Assignment Title");
            et_assignment_title.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(et_assignment_links.getText().toString())) {
            et_assignment_links.setError("Enter Assignment Links");
            et_assignment_links.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
