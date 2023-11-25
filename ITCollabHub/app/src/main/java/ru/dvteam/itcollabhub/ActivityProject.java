package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityProject extends AppCompatActivity {

    int selectedColor;
    private NavController navController;
    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        mail = sPref.getString("UserMail", "");
        int score = sPref.getInt("UserScore", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        LinearLayout profileMenu = findViewById(R.id.profile_menu);
        LinearLayout forumMenu = findViewById(R.id.forum_menu);
        ImageButton plus = findViewById(R.id.plus);

        ImageView bguser = findViewById(R.id.bguser);
        TextView myProjects = findViewById(R.id.my_projects);
        TextView endProjects = findViewById(R.id.end_projects);
        View fragment = findViewById(R.id.nav_host_fragment);
        LinearLayout projectMenu = findViewById(R.id.project_menu);
        ImageView editProfile = findViewById(R.id.edit);
        View my_projects_lin = findViewById(R.id.linear_my_projects);
        View end_projects_lin = findViewById(R.id.linear_end_projects);

        if(score < 100){
            bguser.setBackgroundResource(R.drawable.gradient_blue);
            selectedColor = Color.parseColor("#B20000FF");
            my_projects_lin.setBackgroundResource(R.drawable.blue_line);
            getWindow().setStatusBarColor(ContextCompat.getColor(ActivityProject.this,R.color.blue));
        }
        else if(score < 300){
            bguser.setBackgroundResource(R.drawable.gradient_green);
            selectedColor = Color.parseColor("#B21AFF00");
            my_projects_lin.setBackgroundResource(R.drawable.green_line);
            getWindow().setStatusBarColor(ContextCompat.getColor(ActivityProject.this,R.color.green));
        }
        else if(score < 1000){
            bguser.setBackgroundResource(R.drawable.gradient_brown);
            selectedColor = Color.parseColor("#FFCC7722");
            my_projects_lin.setBackgroundResource(R.drawable.brown_line);
            getWindow().setStatusBarColor(ContextCompat.getColor(ActivityProject.this,R.color.brown));
        }
        else if(score < 2500){
            bguser.setBackgroundResource(R.drawable.gradient_light_gray);
            selectedColor = Color.parseColor("#B2B5B5B5");
            my_projects_lin.setBackgroundResource(R.drawable.light_gray_line);
            getWindow().setStatusBarColor(ContextCompat.getColor(ActivityProject.this,R.color.light_gray));
        }
        else if(score < 7000){
            bguser.setBackgroundResource(R.drawable.gradient_ohra);
            selectedColor = Color.parseColor("#FFE8AA0E");
            my_projects_lin.setBackgroundResource(R.drawable.ohra_line);
            getWindow().setStatusBarColor(ContextCompat.getColor(ActivityProject.this,R.color.ohra));
        }
        else if(score < 17000){
            bguser.setBackgroundResource(R.drawable.gradient_red);
            selectedColor = Color.parseColor("#FF0000");
            my_projects_lin.setBackgroundResource(R.drawable.red_line);
            getWindow().setStatusBarColor(ContextCompat.getColor(ActivityProject.this,R.color.red));
        }
        else if(score < 30000){
            bguser.setBackgroundResource(R.drawable.gradient_orange);
            selectedColor = Color.parseColor("#FFCC7722");
            my_projects_lin.setBackgroundResource(R.drawable.orange_line);
            getWindow().setStatusBarColor(ContextCompat.getColor(ActivityProject.this,R.color.orange));
        }
        else if(score < 50000){
            bguser.setBackgroundResource(R.drawable.gradient_violete);
            selectedColor = Color.parseColor("#4F0070");
            my_projects_lin.setBackgroundResource(R.drawable.violete_line);
            getWindow().setStatusBarColor(ContextCompat.getColor(ActivityProject.this,R.color.violete));
        }
        else{
            bguser.setBackgroundResource(R.drawable.gradient_blue_green);
            selectedColor = Color.parseColor("#FF00C6A2");
            my_projects_lin.setBackgroundResource(R.drawable.blue_green_line);
            getWindow().setStatusBarColor(ContextCompat.getColor(ActivityProject.this,R.color.main_green));
        }

        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProject.this, Profile.class);
                startActivity(intent);
            }
        });
        forumMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityProject.this, CreateProject.class);
                startActivity(intent);
            }
        });
    }
    public String getMail(){
        return mail;
    }
}