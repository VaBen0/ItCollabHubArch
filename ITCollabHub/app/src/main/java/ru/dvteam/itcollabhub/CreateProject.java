package ru.dvteam.itcollabhub;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
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
    private String mediaPath = "";
    private Boolean acces = false;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    ActivityResultLauncher<Intent> resultLauncher;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String mail = sPref.getString("UserMail", "");
        score = sPref.getInt("UserScore", 0);

        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_create_project);
        registerResult();

        //LinearLayout profileMenu = findViewById(R.id.profile_menu);
        //LinearLayout forumMenu = findViewById(R.id.forum_menu);
        TextView adParticip = findViewById(R.id.ad_participiant);
        TextView adActivity = findViewById(R.id.ad_activity);
        Img = findViewById(R.id.pr_logo);
        EditText nameu = findViewById(R.id.projectName);
        EditText description  = findViewById(R.id.description);
        Button sendProject = findViewById(R.id.send);
        View activity_line = findViewById(R.id.linear_projects);
        View particip_line = findViewById(R.id.linear_friends);
        TextView back_button = findViewById(R.id.back);

        if(score < 100){
            activity_line.setBackgroundResource(R.drawable.blue_line);
            sendProject.setBackgroundTintList(ContextCompat.getColorStateList(CreateProject.this, R.color.blue));
        }
        else if(score < 300){
            activity_line.setBackgroundResource(R.drawable.green_line);
            sendProject.setBackgroundTintList(ContextCompat.getColorStateList(CreateProject.this, R.color.green));
        }
        else if(score < 1000){
            activity_line.setBackgroundResource(R.drawable.brown_line);
            sendProject.setBackgroundTintList(ContextCompat.getColorStateList(CreateProject.this, R.color.brown));
        }
        else if(score < 2500){
            activity_line.setBackgroundResource(R.drawable.light_gray_line);
            sendProject.setBackgroundTintList(ContextCompat.getColorStateList(CreateProject.this, R.color.light_gray));
        }
        else if(score < 7000){
            activity_line.setBackgroundResource(R.drawable.ohra_line);
            sendProject.setBackgroundTintList(ContextCompat.getColorStateList(CreateProject.this, R.color.ohra));
        }
        else if(score < 17000){
            activity_line.setBackgroundResource(R.drawable.red_line);
            sendProject.setBackgroundTintList(ContextCompat.getColorStateList(CreateProject.this, R.color.red));
        }
        else if(score < 30000){
            activity_line.setBackgroundResource(R.drawable.orange_line);
            sendProject.setBackgroundTintList(ContextCompat.getColorStateList(CreateProject.this, R.color.orange));
        }
        else if(score < 50000){
            activity_line.setBackgroundResource(R.drawable.violete_line);
            sendProject.setBackgroundTintList(ContextCompat.getColorStateList(CreateProject.this, R.color.violete));
        }
        else{
            activity_line.setBackgroundResource(R.drawable.blue_green_line);
            sendProject.setBackgroundTintList(ContextCompat.getColorStateList(CreateProject.this, R.color.main_green));
        }

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        adParticip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(score < 100){
                    activity_line.setBackgroundColor(0);
                    particip_line.setBackgroundResource(R.drawable.blue_line);
                }
                else if(score < 300){
                    activity_line.setBackgroundColor(0);
                    particip_line.setBackgroundResource(R.drawable.green_line);
                }
                else if(score < 1000){
                    activity_line.setBackgroundColor(0);
                    particip_line.setBackgroundResource(R.drawable.brown_line);
                }
                else if(score < 2500){
                    activity_line.setBackgroundColor(0);
                    particip_line.setBackgroundResource(R.drawable.light_gray_line);
                }
                else if(score < 7000){
                    activity_line.setBackgroundColor(0);
                    particip_line.setBackgroundResource(R.drawable.ohra_line);
                }
                else if(score < 17000){
                    activity_line.setBackgroundColor(0);
                    particip_line.setBackgroundResource(R.drawable.red_line);
                }
                else if(score < 30000){
                    activity_line.setBackgroundColor(0);
                    particip_line.setBackgroundResource(R.drawable.orange_line);
                }
                else if(score < 50000){
                    activity_line.setBackgroundColor(0);
                    particip_line.setBackgroundResource(R.drawable.violete_line);
                }
                else{
                    activity_line.setBackgroundColor(0);
                    particip_line.setBackgroundResource(R.drawable.blue_green_line);
                }

                Bundle bundle = new Bundle();
                bundle.putString("mail", mail);
                navController.navigate(R.id.participant, bundle);
            }
        });
        adActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(score < 100){
                    particip_line.setBackgroundColor(0);
                    activity_line.setBackgroundResource(R.drawable.blue_line);
                }
                else if(score < 300){
                    particip_line.setBackgroundColor(0);
                    activity_line.setBackgroundResource(R.drawable.green_line);
                }
                else if(score < 1000){
                    particip_line.setBackgroundColor(0);
                    activity_line.setBackgroundResource(R.drawable.brown_line);
                }
                else if(score < 2500){
                    particip_line.setBackgroundColor(0);
                    activity_line.setBackgroundResource(R.drawable.light_gray_line);
                }
                else if(score < 7000){
                    particip_line.setBackgroundColor(0);
                    activity_line.setBackgroundResource(R.drawable.ohra_line);
                }
                else if(score < 17000){
                    particip_line.setBackgroundColor(0);
                    activity_line.setBackgroundResource(R.drawable.red_line);
                }
                else if(score < 30000){
                    particip_line.setBackgroundColor(0);
                    activity_line.setBackgroundResource(R.drawable.orange_line);
                }
                else if(score < 50000){
                    particip_line.setBackgroundColor(0);
                    activity_line.setBackgroundResource(R.drawable.violete_line);
                }
                else{
                    particip_line.setBackgroundColor(0);
                    activity_line.setBackgroundResource(R.drawable.blue_green_line);
                }
                navController.navigate(R.id.differentActivity);
            }
        });

        if(Build.VERSION.SDK_INT >= 33) {
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
                String mainId;
                String mainDescription;
                String mainName;
                if(nameu.getText().toString().isEmpty()){
                    mainName = "Отсутствует наименование проекта";
                } else{
                    mainName = nameu.getText().toString();
                }
                if(description.getText().toString().isEmpty()){
                    mainDescription = "Без названия";
                } else{
                    mainDescription = description.getText().toString();
                }
                if(id1.isEmpty()){
                    mainId = "Пользователи были не выбраны";
                }else{
                    mainId = id1;
                }
                String[] purpose1 = purposes_name.split(",");
                String[] purpose2 = purposes.split(",");
                String[] task1 = tasks_name.split(",");
                String[] task2 = tasks.split(",");
                if (!purposes_name.isEmpty()) {
                    for (int i = 0; i < purpose1.length; i++) {
                        if (i != purpose1.length - 1) {
                            if (i > 1) {
                                purposeMain = purposeMain + "," + (i + 1) + ":{" + purpose1[i] + "}{" + purpose2[i] + "}";
                            } else {
                                purposeMain = purposeMain + (i + 1) + ":{" + purpose1[i] + "}{" + purpose2[i] + "}";
                            }
                        } else {
                            purposeMain = purposeMain + (i + 1) + ":{" + purpose1[i] + "}{" + purpose2[i] + "}";
                        }
                    }
                }
                else{
                    purposeMain = "Нет";
                }
                if (!tasks_name.isEmpty()) {
                    for (int i = 0; i < task1.length; i++) {
                        if (i != task1.length - 1) {
                            if (i > 1) {
                                taskMain = taskMain + "," + (i + 1) + ":{" + task1[i] + "}{" + task2[i] + "}";
                            } else {
                                taskMain = taskMain + (i + 1) + ":{" + task1[i] + "}{" + task2[i] + "}";
                            }
                        } else {
                            taskMain = taskMain + (i + 1) + ":{" + task1[i] + "}{" + task2[i] + "}";
                        }
                    }
                }
                else{
                    taskMain = "Нет";
                }
                //Toast.makeText(CreateProject.this, mainId, Toast.LENGTH_SHORT).show();
                if(mediaPath.isEmpty()){
                    PostDatas post = new PostDatas();
                    post.postDataCreateProjectWithoutImage("CreateNewProject", mainName, mail, purposeMain, taskMain,
                            mainDescription, mainId, new CallBackInt() {
                                @Override
                                public void invoke(String res) {
                                    Toast.makeText(CreateProject.this, res, Toast.LENGTH_SHORT).show();
                                    if (res.equals("Успешно")) {
                                        Intent intent = new Intent(CreateProject.this, ActivityProject.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }else{
                    File file = new File(mediaPath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                    PostDatas post = new PostDatas();
                    post.postDataCreateProject("CreateNewProject", mainName, requestBody, mail, purposeMain, taskMain,
                            mainDescription, mainId, new CallBackInt() {
                            @Override
                            public void invoke(String res) {
                                Toast.makeText(CreateProject.this, res, Toast.LENGTH_SHORT).show();
                                if (res.equals("Успешно")) {
                                    Intent intent = new Intent(CreateProject.this, ActivityProject.class);
                                    startActivity(intent);
                                }
                            }
                        });
                }

            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateProject.this, ActivityProject.class);
                startActivity(intent);
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

    public int getScore(){
        return score;
    }
}