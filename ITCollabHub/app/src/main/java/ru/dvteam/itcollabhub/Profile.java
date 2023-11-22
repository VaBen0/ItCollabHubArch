package ru.dvteam.itcollabhub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

public class Profile extends AppCompatActivity {
    View projects_lin;
    View rating_lin;
    View friends_lin;
    int selectedColor;
    NavController navController;
    private int max;
    private int score;
    private int min;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String name = sPref.getString("UserName", "");
        String mail = sPref.getString("UserMail", "");
        score = sPref.getInt("UserScore", 0);

        super.onCreate(savedInstanceState);
        if(score < 100){
            setTheme(R.style.Theme_ITCollabHub_Blue);
        }
        else if(score < 300){
            setTheme(R.style.Theme_ITCollabHub_Green);
        }
        else if(score < 1000){
            setTheme(R.style.Theme_ITCollabHub_Brown);
        }
        else if(score < 2500){
            setTheme(R.style.Theme_ITCollabHub_Gray);
        }
        else if(score < 7000){
            setTheme(R.style.Theme_ITCollabHub_Ohra);
        }
        else if(score < 17000){
            setTheme(R.style.Theme_ITCollabHub_Red);
        }
        else if(score < 30000){
            setTheme(R.style.Theme_ITCollabHub_Orange);
        }
        else if(score < 50000){
            setTheme(R.style.Theme_ITCollabHub_Violete);
        }
        else{
            setTheme(R.style.Theme_ITCollabHub_Green1);
        }
        setContentView(R.layout.activity_profile);

        String s = "Ваши очки: " + score;
        ImageView userCircle = findViewById(R.id.userCircle);
        ImageView bguser = findViewById(R.id.bguser);
        TextView projects = findViewById(R.id.projects);
        TextView friends = findViewById(R.id.friends);
        TextView rating = findViewById(R.id.rating);
        TextView UserName = findViewById(R.id.nameu);
        TextView UserScore = findViewById(R.id.score);
        ImageView loadedImg = findViewById(R.id.loadImg);
        ImageView restartLine = findViewById(R.id.restart);
        projects_lin = findViewById(R.id.linear_projects);
        rating_lin = findViewById(R.id.linear_rating);
        friends_lin = findViewById(R.id.linear_friends);
        projects_lin.setBackgroundColor(Color.BLUE);
        View fragment = findViewById(R.id.nav_host_fragment);
        LinearLayout projectMenu = findViewById(R.id.project_menu);
        LinearLayout profileMenu = findViewById(R.id.profile_menu);
        LinearLayout forumMenu = findViewById(R.id.forum_menu);
        Button changeLog = findViewById(R.id.button6);
        UserName.setText(name);
        UserScore.setText(s);

        if(score < 100){
            bguser.setBackgroundResource(R.drawable.gradient_blue);
            userCircle.setBackgroundResource(R.drawable.circle_blue);
            UserScore.setTextColor(Color.parseColor("#B20000FF"));
            selectedColor = Color.parseColor("#B20000FF");
            projects_lin.setBackgroundColor(selectedColor);
        }
        else if(score < 300){
            bguser.setBackgroundResource(R.drawable.gradient_green);
            userCircle.setBackgroundResource(R.drawable.circle_green);
            UserScore.setTextColor(Color.parseColor("#B21AFF00"));
            selectedColor = Color.parseColor("#B21AFF00");
            projects_lin.setBackgroundColor(selectedColor);
        }
        else if(score < 1000){
            bguser.setBackgroundResource(R.drawable.gradient_brown);
            userCircle.setBackgroundResource(R.drawable.circle_brown);
            UserScore.setTextColor(Color.parseColor("#FFCC7722"));
            selectedColor = Color.parseColor("#FFCC7722");
            projects_lin.setBackgroundColor(selectedColor);
        }
        else if(score < 2500){
            bguser.setBackgroundResource(R.drawable.gradient_light_gray);
            userCircle.setBackgroundResource(R.drawable.circle_light_gray);
            UserScore.setTextColor(Color.parseColor("#B2B5B5B5"));
            selectedColor = Color.parseColor("#B2B5B5B5");
            projects_lin.setBackgroundColor(selectedColor);
        }
        else if(score < 7000){
            bguser.setBackgroundResource(R.drawable.gradient_ohra);
            userCircle.setBackgroundResource(R.drawable.circle_ohra);
            UserScore.setTextColor(Color.parseColor("#FFE8AA0E"));
            selectedColor = Color.parseColor("#FFE8AA0E");
            projects_lin.setBackgroundColor(selectedColor);
        }
        else if(score < 17000){
            bguser.setBackgroundResource(R.drawable.gradient_red);
            userCircle.setBackgroundResource(R.drawable.circle_red);
            UserScore.setTextColor(Color.parseColor("#FF0000"));
            selectedColor = Color.parseColor("#FF0000");
            projects_lin.setBackgroundColor(selectedColor);
        }
        else if(score < 30000){
            bguser.setBackgroundResource(R.drawable.gradient_orange);
            userCircle.setBackgroundResource(R.drawable.circle_orange);
            UserScore.setTextColor(Color.parseColor("#FFCC7722"));
            selectedColor = Color.parseColor("#FFCC7722");
            projects_lin.setBackgroundColor(selectedColor);
        }
        else if(score < 50000){
            bguser.setBackgroundResource(R.drawable.gradient_violete);
            userCircle.setBackgroundResource(R.drawable.circle_violete);
            UserScore.setTextColor(Color.parseColor("#4F0070"));
            selectedColor = Color.parseColor("#4F0070");
            projects_lin.setBackgroundColor(selectedColor);
        }
        else{
            bguser.setBackgroundResource(R.drawable.gradient_blue_green);
            userCircle.setBackgroundResource(R.drawable.circle_blue_green);
            UserScore.setTextColor(Color.parseColor("#FF00C6A2"));
            selectedColor = Color.parseColor("#FF00C6A2");
            projects_lin.setBackgroundColor(selectedColor);
        }


        PostDatas post = new PostDatas();
        post.postDataGetUserData(mail, new CallBackInt2() {
            @Override
            public void invoke(String name, String urlImage, int topScore, String topStatus) {
                String s = "Ваши очки: " + topScore;
                score = topScore;
                status = topStatus;
                UserName.setText(name);
                UserScore.setText(s);
                Glide
                        .with(Profile.this)
                        .load(urlImage)
                        .into(loadedImg);

                if(score < 100){
                    bguser.setBackgroundResource(R.drawable.gradient_blue);
                    userCircle.setBackgroundResource(R.drawable.circle_blue);
                    UserScore.setTextColor(Color.parseColor("#B20000FF"));
                    selectedColor = Color.parseColor("#B20000FF");
                    projects_lin.setBackgroundColor(selectedColor);
                    getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,R.color.blue));
                }
                else if(score < 300){
                    bguser.setBackgroundResource(R.drawable.gradient_green);
                    userCircle.setBackgroundResource(R.drawable.circle_green);
                    UserScore.setTextColor(Color.parseColor("#B21AFF00"));
                    selectedColor = Color.parseColor("#B21AFF00");
                    projects_lin.setBackgroundColor(selectedColor);
                    getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,R.color.green));
                }
                else if(score < 1000){
                    bguser.setBackgroundResource(R.drawable.gradient_brown);
                    userCircle.setBackgroundResource(R.drawable.circle_brown);
                    UserScore.setTextColor(Color.parseColor("#FFCC7722"));
                    selectedColor = Color.parseColor("#FFCC7722");
                    projects_lin.setBackgroundColor(selectedColor);
                    getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,R.color.brown));
                }
                else if(score < 2500){
                    bguser.setBackgroundResource(R.drawable.gradient_light_gray);
                    userCircle.setBackgroundResource(R.drawable.circle_light_gray);
                    UserScore.setTextColor(Color.parseColor("#B2B5B5B5"));
                    selectedColor = Color.parseColor("#B2B5B5B5");
                    projects_lin.setBackgroundColor(selectedColor);
                    getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,R.color.light_gray));
                }
                else if(score < 7000){
                    bguser.setBackgroundResource(R.drawable.gradient_ohra);
                    userCircle.setBackgroundResource(R.drawable.circle_ohra);
                    UserScore.setTextColor(Color.parseColor("#FFE8AA0E"));
                    selectedColor = Color.parseColor("#FFE8AA0E");
                    projects_lin.setBackgroundColor(selectedColor);
                    getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,R.color.ohra));
                }
                else if(score < 17000){
                    bguser.setBackgroundResource(R.drawable.gradient_red);
                    userCircle.setBackgroundResource(R.drawable.circle_red);
                    UserScore.setTextColor(Color.parseColor("#FF0000"));
                    selectedColor = Color.parseColor("#FF0000");
                    projects_lin.setBackgroundColor(selectedColor);
                    getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,R.color.red));
                }
                else if(score < 30000){
                    bguser.setBackgroundResource(R.drawable.gradient_orange);
                    userCircle.setBackgroundResource(R.drawable.circle_orange);
                    UserScore.setTextColor(Color.parseColor("#FFCC7722"));
                    selectedColor = Color.parseColor("#FFCC7722");
                    projects_lin.setBackgroundColor(selectedColor);
                    getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,R.color.orange));
                }
                else if(score < 50000){
                    bguser.setBackgroundResource(R.drawable.gradient_violete);
                    userCircle.setBackgroundResource(R.drawable.circle_violete);
                    UserScore.setTextColor(Color.parseColor("#4F0070"));
                    selectedColor = Color.parseColor("#4F0070");
                    projects_lin.setBackgroundColor(selectedColor);
                    getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,R.color.violete));
                }
                else{
                    bguser.setBackgroundResource(R.drawable.gradient_blue_green);
                    userCircle.setBackgroundResource(R.drawable.circle_blue_green);
                    UserScore.setTextColor(Color.parseColor("#FF00C6A2"));
                    selectedColor = Color.parseColor("#FF00C6A2");
                    projects_lin.setBackgroundColor(selectedColor);
                    getWindow().setStatusBarColor(ContextCompat.getColor(Profile.this,R.color.main_green));
                }

                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("UserName", name);
                ed.putInt("UserScore", topScore);
                ed.putString("UrlImg", urlImage);
                ed.apply();
            }
        });

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projects_lin.setBackgroundColor(selectedColor);
                friends_lin.setBackgroundColor(0);
                rating_lin.setBackgroundColor(0);
                navController.navigate(R.id.projects);
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projects_lin.setBackgroundColor(0);
                friends_lin.setBackgroundColor(selectedColor);
                rating_lin.setBackgroundColor(0);
                fragment.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int height = fragment.getMeasuredHeight();

                Bundle bundle = new Bundle();
                bundle.putString("mail", mail);
                bundle.putInt("height", height);

                navController.navigate(R.id.friends, bundle);
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projects_lin.setBackgroundColor(0);
                friends_lin.setBackgroundColor(0);
                rating_lin.setBackgroundColor(selectedColor);

                Bundle bundle = new Bundle();
                bundle.putInt("score", score);
                bundle.putString("status", status);

                navController.navigate(R.id.rating, bundle);

            }
        });

        restartLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(getIntent());
                finish();
            }
        });
        projectMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Project.class);
                startActivity(intent);
            }
        });
        forumMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



}