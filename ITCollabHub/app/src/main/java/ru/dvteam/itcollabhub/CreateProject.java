package ru.dvteam.itcollabhub;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class CreateProject extends AppCompatActivity {
    private ImageView Img;
    private NavController navController;
    private EditText description;
    private static final int PICK_IMAGES_CODE = 0;
    private String purposes_name = "", purposes = "", tasks_name = "", tasks = "";
    private String id1 = "";
    private String mediaPath;
    private Boolean acces = false;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        registerResult();

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String mail = sPref.getString("UserMail", "");

        LinearLayout profileMenu = findViewById(R.id.profile_menu);
        LinearLayout forumMenu = findViewById(R.id.forum_menu);
        TextView adParticip = findViewById(R.id.ad_participiant);
        TextView adActivity = findViewById(R.id.ad_activity);
        Img = findViewById(R.id.pr_logo);
        EditText nameu = findViewById(R.id.textView14);
        EditText description  = findViewById(R.id.description);
        Button sendProject = findViewById(R.id.send);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateProject.this, Profile.class);
                startActivity(intent);
            }
        });
        forumMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        adParticip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("mail", mail);
                navController.navigate(R.id.participant, bundle);
            }
        });
        adActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.differentActivity);
            }
        });

        if(android.os.Build.VERSION.SDK_INT >= 33) {
            Img.setOnClickListener(view -> pickImage());
        }
        else{
            Img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(CreateProject.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CreateProject.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    } else {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
                        startActivityForResult(Intent.createChooser(intent, "Select Image(s)"), PICK_IMAGES_CODE);
                    }
                }
            });
        }
        sendProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CreateProject.this, purposes_name + " " + purposes + " " + tasks_name + " " + tasks + ", " + id1, Toast.LENGTH_SHORT).show();
                String purposeMain = "";
                String taskMain = "";
                String idMain = "";
                String[] purpose1 = purposes_name.split(",");
                String[] purpose2 = purposes.split(",");
                String[] task1 = tasks_name.split(",");
                String[] task2 = tasks.split(",");
                String[] id = id1.split(",");
                for(int i = 0; i < purpose1.length; i++){
                    if(i != purpose1.length - 1){
                        if(i > 1){
                            purposeMain = purposeMain + "," + (i+1) + ":{" + purpose1[i] + "}{" + purpose2[i] + "}";
                        }
                        else{
                            purposeMain = purposeMain + (i+1) + ":{" + purpose1[i] + "}{" + purpose2[i] + "}";
                        }
                    }
                    else{
                        purposeMain = purposeMain + (i+1) + ":{" + purpose1[i] + "}{" + purpose2[i] + "}";
                    }
                }
                for(int i = 0; i < task1.length; i++){
                    if(i != task1.length - 1){
                        if(i > 1){
                            taskMain = taskMain + "," + (i+1) + ":{" + task1[i] + "}{" + task2[i] + "}";
                        }
                        else{
                            taskMain = taskMain + (i+1) + ":{" + task1[i] + "}{" + task2[i] + "}";
                        }
                    }
                    else{
                        taskMain = taskMain + (i+1) + ":{" + task1[i] + "}{" + task2[i] + "}";
                    }
                }
                Toast.makeText(CreateProject.this, purposeMain + " | " +taskMain + " | " + id1, Toast.LENGTH_SHORT).show();

                File file = new File(mediaPath);
                RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                PostDatas post = new PostDatas();
                post.postDataCreateProject("CreateNewProject", nameu.getText().toString(), requestBody, mail, purposeMain, taskMain,
                        description.getText().toString(), id1, new CallBackInt() {
                    @Override
                    public void invoke(String res) {
                        Toast.makeText(CreateProject.this, res, Toast.LENGTH_SHORT).show();
                        if (res.equals("Сохранено")) {
                            Intent intent = new Intent(CreateProject.this, ActivityProject.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    public void taskSet(){
        NavController navController2 = Navigation.findNavController(this, R.id.nav_host_fragment2);
        navController2.navigate(R.id.clop3);
    }
    public void deadlineSet(){
        NavController navController2 = Navigation.findNavController(this, R.id.nav_host_fragment2);
        navController2.navigate(R.id.clop2);
    }
    public void purpose(){
        NavController navController2 = Navigation.findNavController(this, R.id.nav_host_fragment2);
        navController2.navigate(R.id.clop1);
    }

    public void setPurp(String purpName, String purp){
        if(purposes_name.isEmpty()){
            purposes_name += purpName;
            purposes += purp;
        }
        else{
            purposes_name = purposes_name + "," + purpName;
            purposes = purposes + "," + purp;
        }
    }
    public void setTask(String taskName, String task){
        if(tasks_name.isEmpty()){
            tasks_name += taskName;
            tasks = task;
        }
        else{
            tasks_name = tasks_name + "," + taskName;
            tasks = tasks + "," + task;
        }
    }
    public void setId(String id){
        if(id1.isEmpty()){
            id1 = id;
        }
        else{
            id1 = id1 + "," + id;
        }
    }

    private void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image(s)"), PICK_IMAGES_CODE);
            } else {
                Toast.makeText(CreateProject.this, "You loser", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Button btn = findViewById(R.id.saveBut);
        if (requestCode == PICK_IMAGES_CODE){

            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                Img.setImageURI(imageUri);
                cursor.close();
                acces = true;
            }

        }
    }

    private void registerResult(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try{
                            Uri imageUri = result.getData().getData();
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};

                            Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                            assert cursor != null;
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            mediaPath = cursor.getString(columnIndex);
                            Img.setImageURI(imageUri);
                            cursor.close();
                            acces = true;
                        }catch (Exception e){
                            Toast.makeText(CreateProject.this, "LOSER", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}