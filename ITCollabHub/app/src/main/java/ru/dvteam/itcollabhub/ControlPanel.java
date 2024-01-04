package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.dvteam.itcollabhub.databinding.ActivityControlPanelBinding;

public class ControlPanel extends AppCompatActivity {

    ActivityControlPanelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityControlPanelBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.blue_transperent));

        Bundle arguments = getIntent().getExtras();

        assert arguments != null;
        String id = arguments.getString("projectId");

        PostDatas postDatas = new PostDatas();
        postDatas.postDataGetProjectInformation("GetProjectMainInformation", id, new CallBackInt4() {
            @Override
            public void invoke(String name, String photoUrl, String descript) {
                binding.nameProject.setText(name);
                Glide
                        .with(ControlPanel.this)
                        .load(photoUrl)
                        .into(binding.prLogo);
            }
        });

        for (int i = 0; i < 5; i++) {
            View custom = getLayoutInflater().inflate(R.layout.reminder, null);

            binding.reminderPlace.addView(custom);
        }
    }
}