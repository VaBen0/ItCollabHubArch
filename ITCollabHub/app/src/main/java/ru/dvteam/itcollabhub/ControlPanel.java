package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;

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

        for (int i = 0; i < 5; i++) {
            View custom = getLayoutInflater().inflate(R.layout.reminder, null);

            binding.reminderPlace.addView(custom);
        }
    }
}