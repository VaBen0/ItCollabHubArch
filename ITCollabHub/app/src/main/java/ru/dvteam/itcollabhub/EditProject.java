package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;

import ru.dvteam.itcollabhub.databinding.ActivityEditProjectBinding;

public class EditProject extends AppCompatActivity {

    ActivityEditProjectBinding binding;

    private NavController navController;
    String id, title, description, prPhoto, mail;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        mail = sPref.getString("UserMail", "");
        score = sPref.getInt("UserScore", 0);

        binding = ActivityEditProjectBinding.inflate(getLayoutInflater());
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_create_project1);

        setContentView(binding.getRoot());

        Bundle arguments = getIntent().getExtras();
        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        if(score < 100){
            binding.linearParticipiant.setBackgroundColor(0);
            binding.linearEdit.setBackgroundResource(R.drawable.blue_line);
        }
        else if(score < 300){
            binding.linearParticipiant.setBackgroundColor(0);
            binding.linearEdit.setBackgroundResource(R.drawable.green_line);
        }
        else if(score < 1000){
            binding.linearParticipiant.setBackgroundColor(0);
            binding.linearEdit.setBackgroundResource(R.drawable.brown_line);
        }
        else if(score < 2500){
            binding.linearParticipiant.setBackgroundColor(0);
            binding.linearEdit.setBackgroundResource(R.drawable.light_gray_line);
        }
        else if(score < 7000){
            binding.linearParticipiant.setBackgroundColor(0);
            binding.linearEdit.setBackgroundResource(R.drawable.ohra_line);
        }
        else if(score < 17000){
            binding.linearParticipiant.setBackgroundColor(0);
            binding.linearEdit.setBackgroundResource(R.drawable.red_line);
        }
        else if(score < 30000){
            binding.linearParticipiant.setBackgroundColor(0);
            binding.linearEdit.setBackgroundResource(R.drawable.orange_line);
        }
        else if(score < 50000){
            binding.linearParticipiant.setBackgroundColor(0);
            binding.linearEdit.setBackgroundResource(R.drawable.violete_line);
        }
        else{
            binding.linearParticipiant.setBackgroundColor(0);
            binding.linearEdit.setBackgroundResource(R.drawable.blue_green_line);
        }
        assert arguments != null;
        id = arguments.getString("projectId");
        title = arguments.getString("projectTitle");
        prPhoto = arguments.getString("projectUrlPhoto");
        description = arguments.getString("projectDescription");
        navController.navigate(R.id.editProjectFragment);

        binding.projectName.setText(title);
        Glide
                .with(EditProject.this)
                .load(prPhoto)
                .into(binding.prLogo);
        binding.tint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.editFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score < 100){
                    binding.linearParticipiant.setBackgroundColor(0);
                    binding.linearEdit.setBackgroundResource(R.drawable.blue_line);
                }
                else if(score < 300){
                    binding.linearParticipiant.setBackgroundColor(0);
                    binding.linearEdit.setBackgroundResource(R.drawable.green_line);
                }
                else if(score < 1000){
                    binding.linearParticipiant.setBackgroundColor(0);
                    binding.linearEdit.setBackgroundResource(R.drawable.brown_line);
                }
                else if(score < 2500){
                    binding.linearParticipiant.setBackgroundColor(0);
                    binding.linearEdit.setBackgroundResource(R.drawable.light_gray_line);
                }
                else if(score < 7000){
                    binding.linearParticipiant.setBackgroundColor(0);
                    binding.linearEdit.setBackgroundResource(R.drawable.ohra_line);
                }
                else if(score < 17000){
                    binding.linearParticipiant.setBackgroundColor(0);
                    binding.linearEdit.setBackgroundResource(R.drawable.red_line);
                }
                else if(score < 30000){
                    binding.linearParticipiant.setBackgroundColor(0);
                    binding.linearEdit.setBackgroundResource(R.drawable.orange_line);
                }
                else if(score < 50000){
                    binding.linearParticipiant.setBackgroundColor(0);
                    binding.linearEdit.setBackgroundResource(R.drawable.violete_line);
                }
                else{
                    binding.linearParticipiant.setBackgroundColor(0);
                    binding.linearEdit.setBackgroundResource(R.drawable.blue_green_line);
                }
                navController.navigate(R.id.editProjectFragment);
            }
        });
        binding.participiantFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score < 100){
                    binding.linearEdit.setBackgroundColor(0);
                    binding.linearParticipiant.setBackgroundResource(R.drawable.blue_line);
                }
                else if(score < 300){
                    binding.linearEdit.setBackgroundColor(0);
                    binding.linearParticipiant.setBackgroundResource(R.drawable.green_line);
                }
                else if(score < 1000){
                    binding.linearEdit.setBackgroundColor(0);
                    binding.linearParticipiant.setBackgroundResource(R.drawable.brown_line);
                }
                else if(score < 2500){
                    binding.linearEdit.setBackgroundColor(0);
                    binding.linearParticipiant.setBackgroundResource(R.drawable.light_gray_line);
                }
                else if(score < 7000){
                    binding.linearEdit.setBackgroundColor(0);
                    binding.linearParticipiant.setBackgroundResource(R.drawable.ohra_line);
                }
                else if(score < 17000){
                    binding.linearEdit.setBackgroundColor(0);
                    binding.linearParticipiant.setBackgroundResource(R.drawable.red_line);
                }
                else if(score < 30000){
                    binding.linearEdit.setBackgroundColor(0);
                    binding.linearParticipiant.setBackgroundResource(R.drawable.orange_line);
                }
                else if(score < 50000){
                    binding.linearEdit.setBackgroundColor(0);
                    binding.linearParticipiant.setBackgroundResource(R.drawable.violete_line);
                }
                else{
                    binding.linearEdit.setBackgroundColor(0);
                    binding.linearParticipiant.setBackgroundResource(R.drawable.blue_green_line);
                }
                navController.navigate(R.id.participantEditProject);
            }
        });

    }

    public void confirm(){
        binding.tint.setVisibility(View.VISIBLE);
        binding.panel.setVisibility(View.VISIBLE);
        binding.confirmationText.setVisibility(View.VISIBLE);
        binding.yesBut.setVisibility(View.VISIBLE);
        binding.noBut.setVisibility(View.VISIBLE);
        binding.projectName.clearFocus();
        binding.yesBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tint.setVisibility(View.GONE);
                binding.panel.setVisibility(View.GONE);
                binding.confirmationText.setVisibility(View.GONE);
                binding.yesBut.setVisibility(View.GONE);
                binding.noBut.setVisibility(View.GONE);
            }
        });
        binding.noBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tint.setVisibility(View.GONE);
                binding.panel.setVisibility(View.GONE);
                binding.confirmationText.setVisibility(View.GONE);
                binding.yesBut.setVisibility(View.GONE);
                binding.noBut.setVisibility(View.GONE);
            }
        });
    }
    public String getDescription(){
        return description;
    }
    public int getScore(){return score;}
}