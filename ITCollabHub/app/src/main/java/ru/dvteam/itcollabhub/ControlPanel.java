package ru.dvteam.itcollabhub;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import ru.dvteam.itcollabhub.databinding.ActivityControlPanelBinding;

public class ControlPanel extends AppCompatActivity {

    ActivityControlPanelBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        int score = sPref.getInt("UserScore", 0);

        binding = ActivityControlPanelBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        if(score < 100){
            binding.bguser.setBackgroundResource(R.drawable.gradient_blue);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.blue_transperent));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.blue));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.blue));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.blue));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.blue));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_bg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_bg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_bg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.zadaniaProgress.setBackgroundResource(R.drawable.custom_progress_bar_bg);
            binding.zadaniaProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 300){
            binding.bguser.setBackgroundResource(R.drawable.gradient_green);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.green));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.green));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.green));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.green));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.green));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_gg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_gg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_gg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.zadaniaProgress.setBackgroundResource(R.drawable.custom_progress_bar_gg);
            binding.zadaniaProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 1000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_brown);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.brown));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.brown));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.brown));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.brown));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.brown));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_brg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_brg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_brg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.zadaniaProgress.setBackgroundResource(R.drawable.custom_progress_bar_brg);
            binding.zadaniaProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 2500){
            binding.bguser.setBackgroundResource(R.drawable.gradient_light_gray);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.light_gray));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.light_gray));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.light_gray));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.light_gray));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.light_gray));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_lgg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_lgg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_lgg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.zadaniaProgress.setBackgroundResource(R.drawable.custom_progress_bar_lgg);
            binding.zadaniaProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 7000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_ohra);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.ohra));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.ohra));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.ohra));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.ohra));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.ohra));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_ohg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_ohg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_ohg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.zadaniaProgress.setBackgroundResource(R.drawable.custom_progress_bar_ohg);
            binding.zadaniaProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 17000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_red);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.red));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.red));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.red));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.red));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.red));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_rg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_rg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_rg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.zadaniaProgress.setBackgroundResource(R.drawable.custom_progress_bar_rg);
            binding.zadaniaProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 30000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_orange);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.orange));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.orange));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.orange));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.orange));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.orange));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_og);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_og);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_og);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.zadaniaProgress.setBackgroundResource(R.drawable.custom_progress_bar_og);
            binding.zadaniaProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 50000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_violete);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.violete));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.violete));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.violete));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.violete));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.violete));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_vg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_vg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_vg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.zadaniaProgress.setBackgroundResource(R.drawable.custom_progress_bar_vg);
            binding.zadaniaProgress.setProgressDrawable(progressDrawable);
        }
        else{
            binding.bguser.setBackgroundResource(R.drawable.gradient_blue_green);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.main_green));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.main_green));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.main_green));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.main_green));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.main_green));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_mgg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_mgg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_mgg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.zadaniaProgress.setBackgroundResource(R.drawable.custom_progress_bar_mgg);
            binding.zadaniaProgress.setProgressDrawable(progressDrawable);
        }

        Bundle arguments = getIntent().getExtras();

        assert arguments != null;
        String id = arguments.getString("projectId");

        /*PostDatas postDatas = new PostDatas();
        postDatas.postDataGetProjectInformation("GetProjectMainInformation", id, new CallBackInt4() {
            @Override
            public void invoke(String name, String photoUrl, String descript) {
                binding.nameProject.setText(name);
                Glide
                        .with(ControlPanel.this)
                        .load(photoUrl)
                        .into(binding.prLogo);
            }
        });*/

        for (int i = 0; i < 5; i++) {
            View custom = getLayoutInflater().inflate(R.layout.reminder, null);

            binding.reminderPlace.addView(custom);
        }
    }
}