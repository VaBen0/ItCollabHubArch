package ru.dvteam.itcollabhub;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import ru.dvteam.itcollabhub.databinding.ActivityMain2Binding;
import ru.dvteam.itcollabhub.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {
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
        setContentView(R.layout.activity_main2);


        String s = "Ваши очки: " + score;
        ImageView userCircle = findViewById(R.id.userCircle);
        ImageView bguser = findViewById(R.id.bguser);
        TextView projects = findViewById(R.id.projects);
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
                        .with(MainActivity2.this)
                        .load(urlImage)
                        .into(loadedImg);

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

                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("UserName", name);
                ed.putInt("UserScore", topScore);
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
                //Navigation.findNavController(v).navigate(R.id.projects);
                navController.navigate(R.id.projects);
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projects_lin.setBackgroundColor(0);
                friends_lin.setBackgroundColor(selectedColor);
                rating_lin.setBackgroundColor(0);
                navController.navigate(R.id.friends);
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
    }
}