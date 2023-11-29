package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class UsersProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_users_project);

        Bundle arguments = getIntent().getExtras();

        String id = arguments.getString("projectId");

        TextView projectName = findViewById(R.id.projectName);
        TextView description = findViewById(R.id.description);
        ImageView projectLogo = findViewById(R.id.pr_logo);

        PostDatas postDatas = new PostDatas();
        postDatas.postDataGetProjectInformation("GetProjectMainInformation", id, new CallBackInt4() {
            @Override
            public void invoke(String name, String photoUrl, String descript) {
                projectName.setText(name);
                Glide
                        .with(UsersProject.this)
                        .load(photoUrl)
                        .into(projectLogo);
                description.setText(descript);
            }
        });
    }

}