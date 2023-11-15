package ru.dvteam.itcollabhub;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {
    private NavController navController;
    View projects_lin;
    View rating_lin;
    View friends_lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String name = sPref.getString("UserName", "");
        String mail = sPref.getString("UserMail", "");
        int score = sPref.getInt("UserScore", 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String s = "Ваши очки: " + score;
        TextView projects = findViewById(R.id.projects);
        ImageView bgUser = findViewById(R.id.bguser);
        TextView friends = findViewById(R.id.friends);
        TextView rating = findViewById(R.id.rating);
        TextView UserName = findViewById(R.id.nameu);
        TextView UserScore = findViewById(R.id.score);
        ImageView loadedImg = findViewById(R.id.loadImg);
        projects_lin = findViewById(R.id.linear_projects);
        rating_lin = findViewById(R.id.linear_rating);
        friends_lin = findViewById(R.id.linear_friends);
        projects_lin.setBackgroundColor(Color.BLUE);
        UserName.setText(name);
        UserScore.setText(s);

        if(score < 100){
            bgUser.setBackgroundResource(R.drawable.gradient_blue);
        }
        else if(score < 300){
            bgUser.setBackgroundResource(R.drawable.gradient_green);
        }
        else if(score < 1000){
            bgUser.setBackgroundResource(R.drawable.gradient_brown);
        }
        else if(score < 2500){
            bgUser.setBackgroundResource(R.drawable.gradient_light_gray);
        }
        else if(score < 7000){
            bgUser.setBackgroundResource(R.drawable.gradient_ohra);
        }
        else if(score < 17000){
            bgUser.setBackgroundResource(R.drawable.gradient_red);
        }
        else if(score < 30000){
            bgUser.setBackgroundResource(R.drawable.gradient_orange);
        }
        else if(score < 50000){
            bgUser.setBackgroundResource(R.drawable.gradient_violete);
        }
        else{
            bgUser.setBackgroundResource(R.drawable.gradient_blue_green);
        }


        PostDatas post = new PostDatas();
        post.postDataGetUserData(mail, new CallBackInt2() {
            @Override
            public void invoke(String name, String urlImage, int topScore, String topStatus) {
                String s = "Ваши очки: " + topScore;
                UserName.setText(name);
                UserScore.setText(s);
                Glide
                        .with(MainActivity2.this)
                        .load(urlImage)
                        .into(loadedImg);

                if(topScore < 100){
                    bgUser.setBackgroundResource(R.drawable.gradient_blue);
                }
                else if(topScore < 300){
                    bgUser.setBackgroundResource(R.drawable.gradient_green);
                }
                else if(topScore < 1000){
                    bgUser.setBackgroundResource(R.drawable.gradient_brown);
                }
                else if(topScore < 2500){
                    bgUser.setBackgroundResource(R.drawable.gradient_light_gray);
                }
                else if(topScore < 7000){
                    bgUser.setBackgroundResource(R.drawable.gradient_ohra);
                }
                else if(topScore < 17000){
                    bgUser.setBackgroundResource(R.drawable.gradient_red);
                }
                else if(topScore < 30000){
                    bgUser.setBackgroundResource(R.drawable.gradient_orange);
                }
                else if(topScore < 50000){
                    bgUser.setBackgroundResource(R.drawable.gradient_violete);
                }
                else{
                    bgUser.setBackgroundResource(R.drawable.gradient_blue_green);
                }

                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("UserName", name);
                ed.putInt("UserScore", topScore);
                ed.apply();
            }
        });

        //navController = Navigation.findNavController(this, R.id.fragmentContainerView);

        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projects_lin.setBackgroundColor(Color.BLUE);
                friends_lin.setBackgroundColor(0);
                rating_lin.setBackgroundColor(0);

                //navController.navigate(R.id.projects);
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projects_lin.setBackgroundColor(0);
                friends_lin.setBackgroundColor(Color.BLUE);
                rating_lin.setBackgroundColor(0);

                //navController.navigate(R.id.friends);
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projects_lin.setBackgroundColor(0);
                friends_lin.setBackgroundColor(0);
                rating_lin.setBackgroundColor(Color.BLUE);

                //navController.navigate(R.id.rating);
            }
        });
    }
}