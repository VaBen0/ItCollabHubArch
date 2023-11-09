package ru.dvteam.itcollabhub;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;

public class MainActivity2 extends AppCompatActivity {
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String name = sPref.getString("UserName", "");
        String mail = sPref.getString("UserMail", "");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView projects = findViewById(R.id.projects);
        TextView friends = findViewById(R.id.friends);
        TextView rating = findViewById(R.id.rating);
        TextView UserName = findViewById(R.id.nameu);
        TextView UserScore = findViewById(R.id.score);
        ImageView loadedImg = findViewById(R.id.loadImg);
        UserName.setText(name);

        PostDatas post = new PostDatas();
        post.postDataGetUserData(mail, new CallBackInt2() {
            @Override
            public void invoke(String name, String urlImage, String topScore, String topStatus) {
                UserName.setText(name);
                UserScore.setText(topScore);
                Glide
                        .with(MainActivity2.this)
                        .load("https://serveritcollabhub.development-team.ru/file_uploads/Images/lol.png")
                        .into(loadedImg);
            }
        });


        /*SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String savedText = sPref.getString("UserName", "");*/

        navController = Navigation.findNavController(this, R.id.fragmentContainerView);

        projects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               navController.navigate(R.id.projects);
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.friends);
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.rating);
            }
        });


        //TextView txt = findViewById(R.id.collaborotory);
        //txt.setText("Добрый вечер, " + savedText + "!");
    }
}