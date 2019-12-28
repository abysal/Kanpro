package com.example.collegeapp.Fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collegeapp.API.UserAPI;
import com.example.collegeapp.Activity.Dashboard;
import com.example.collegeapp.Activity.UpdateProfile;
import com.example.collegeapp.Model.UserImage;
import com.example.collegeapp.R;
import com.example.collegeapp.Url.Url;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfile extends Fragment {
    Button btn_select_profile_image, btn_upload_profile_image,btn_profile_details;
    EditText user_image1;
    String user_image;
    CircleImageView profile_image;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_IMAGE = 2;

    TextView first_name, last_name, email, gender, batch, section, user_name, password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    UserAPI userAPI;
    Bitmap mBitmap;
    Uri imageUri;
    String imagePath;
    private String imageName;


    public UserProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        getActivity().setTitle("Profile");
        btn_select_profile_image = (Button) view.findViewById(R.id.btn_select_profile_image);
        btn_upload_profile_image = (Button) view.findViewById(R.id.btn_upload_profile_image);
        btn_profile_details=(Button)view.findViewById(R.id.btn_profile_details);
        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);

        first_name = (TextView) view.findViewById(R.id.first_name);
        last_name = (TextView) view.findViewById(R.id.last_name);
        email = (TextView) view.findViewById(R.id.email);
        gender = (TextView) view.findViewById(R.id.gender);
        batch = (TextView) view.findViewById(R.id.batch);
        section = (TextView) view.findViewById(R.id.section);
        user_name = (TextView) view.findViewById(R.id.user_name);
        password = (TextView) view.findViewById(R.id.password);

        user_image1=(EditText)view.findViewById(R.id.user_image);


        preferences = getActivity().getSharedPreferences("Userinfo", Context.MODE_PRIVATE);

        String id1 = preferences.getString("id", "");
        String first_name1 = preferences.getString("first_name", "");
        String last_name1 = preferences.getString("last_name", "");
        String email1 = preferences.getString("email", "");

        String image = preferences.getString("user_image", "");
        String fullpath = Url.BASE_URL + image;
        Picasso.get().load(fullpath).into(profile_image);

        String gender1 = preferences.getString("gender", "");
        String batch1 = preferences.getString("batch", "");
        String section1 = preferences.getString("section", "");
        String user_name1 = preferences.getString("user_name", "");

        first_name.setText("First Name: " + first_name1);
        last_name.setText("Last Name: " + last_name1);
        email.setText("Email: " + email1);
        gender.setText("Gender: " + gender1);
        batch.setText("Batch: " + batch1);
        section.setText("Section: " + section1);
        user_name.setText("User Name: " + user_name1);
        password.setText("Password: *********");

        btn_select_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(getActivity(), btn_select_profile_image);
                popupMenu.getMenuInflater().inflate(R.menu.profile_image_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.action_camera:
                                dispatchTakePictureIntent();
                                break;
                            case R.id.action_gallery:
                                Intent gallery = new Intent();
                                gallery.setType("image/*");
                                gallery.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        btn_upload_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBitmap!=null){
                    updateImage();
                    Log.d("Image file",mBitmap+"");
                }
                else{
                    Toast.makeText(getActivity(), "Please select an image first", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_profile_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), UpdateProfile.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    if (extras == null) {
                        return;
                    }
                    mBitmap = (Bitmap) extras.get("data");
                    profile_image.setImageBitmap(mBitmap);
                }
                break;

            case PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    if (data==null){
                        Toast.makeText(getActivity(), "Please select an image", Toast.LENGTH_SHORT).show();
                    }
                    imageUri=data.getData();
                    try {
                        mBitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);
                        profile_image.setImageBitmap(mBitmap);
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    public void createInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userAPI = retrofit.create(UserAPI.class);
    }


    private void addImage(Bitmap bm){

        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] bytes=stream.toByteArray();



        try{
            File file=new File(getActivity().getCacheDir(),"image.jpeg");
            file.createNewFile();
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();

            RequestBody rb=RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part img=MultipartBody.Part.createFormData("user_image",file.getName(),rb);
            createInstance();

            Call<UserImage> imageCall = userAPI.uploadImage(img);
            StrictMode();
            try{
                Response<UserImage> userImageResponse=imageCall.execute();
                user_image="uploads\\"+userImageResponse.body().getUser_image();

            }
            catch (Exception e){
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateImage(){
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setMessage("Uploading Image...");
        pd.setCancelable(false);
        pd.show();
        addImage(mBitmap);
        preferences=getActivity().getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
        String id1=preferences.getString("id","");
         createInstance();
         UserImage userImage=new UserImage(user_image);
        Call<Void> addCall=userAPI.updateImage(id1,userImage);
        addCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Error:"+response.code(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    return;


                }
                Toast.makeText(getActivity(), "Profile Image Updated Successfully", Toast.LENGTH_SHORT).show();
                editor=preferences.edit();
                editor.putString("user_image",user_image).commit();
                Intent intent=new Intent(getContext(), Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                pd.dismiss();
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
