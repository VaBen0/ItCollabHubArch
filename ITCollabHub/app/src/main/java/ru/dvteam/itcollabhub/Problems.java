package ru.dvteam.itcollabhub;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import ru.dvteam.itcollabhub.databinding.ActivityProblemsBinding;

public class Problems extends AppCompatActivity {

    ActivityProblemsBinding binding;
    int countProblems = 0;
    private NavController navController;
    private EditText description;
    private static final int PICK_IMAGES_CODE = 0;
    private String mediaPath = "";
    private Boolean acces = false;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String mail = sPref.getString("UserMail", "");
        int score = sPref.getInt("UserScore", 0);

        binding = ActivityProblemsBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        registerResult();

        if(score < 100){
            binding.bguser.setBackgroundResource(R.drawable.gradient_blue);
            getWindow().setStatusBarColor(ContextCompat.getColor(Problems.this,R.color.blue));
        }
        else if(score < 300){
            binding.bguser.setBackgroundResource(R.drawable.gradient_green);
            getWindow().setStatusBarColor(ContextCompat.getColor(Problems.this,R.color.green));
        }
        else if(score < 1000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_brown);
            getWindow().setStatusBarColor(ContextCompat.getColor(Problems.this,R.color.brown));
        }
        else if(score < 2500){
            binding.bguser.setBackgroundResource(R.drawable.gradient_light_gray);
            getWindow().setStatusBarColor(ContextCompat.getColor(Problems.this,R.color.light_gray));
        }
        else if(score < 7000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_ohra);
            getWindow().setStatusBarColor(ContextCompat.getColor(Problems.this,R.color.ohra));
        }
        else if(score < 17000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_red);
            getWindow().setStatusBarColor(ContextCompat.getColor(Problems.this,R.color.red));
        }
        else if(score < 30000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_orange);
            getWindow().setStatusBarColor(ContextCompat.getColor(Problems.this,R.color.orange));
        }
        else if(score < 50000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_violete);
            getWindow().setStatusBarColor(ContextCompat.getColor(Problems.this,R.color.violete));
        }
        else{
            binding.bguser.setBackgroundResource(R.drawable.gradient_blue_green);
            getWindow().setStatusBarColor(ContextCompat.getColor(Problems.this,R.color.main_green));
        }


        /*Glide.with(this)
                .load("https://serveritcollabhub.development-team.ru/project_image/moaiitcollabhub.png")
                .into(binding.textView8);*/

        Bundle arguments = getIntent().getExtras();

        assert arguments != null;
        String id = arguments.getString("projectId");
        String prId = arguments.getString("projectId1");
        String projectTitle = arguments.getString("projectTitle");
        String photoProject = arguments.getString("projectUrlPhoto");

        binding.nameProject.setText(projectTitle);
        Glide
                .with(Problems.this)
                .load(photoProject)
                .into(binding.prLogo);
        Glide
                .with(Problems.this)
                .load(photoProject)
                .into(binding.imagePurp);

        PostDatas post = new PostDatas();
        post.postDataGetProblems("GetProblems", id, new CallBackInt() {
            @Override
            public void invoke(String res) {
                String[] inf = res.split("\uD83D\uDD70");
                //Toast.makeText(Problems.this, res, Toast.LENGTH_SHORT).show();
                for(int i = 0; i < inf.length; i += 4){
                    View custom = getLayoutInflater().inflate(R.layout.problem_panel, null);
                    ImageView loadImg = custom.findViewById(R.id.imagePurp);
                    TextView name = custom.findViewById(R.id.name);
                    TextView descr = custom.findViewById(R.id.description1);
                    View back = custom.findViewById(R.id.view8);

                    if(inf[i + 2].equals("1")){
                        back.setBackgroundResource(R.drawable.green_transperent);
                    }

                    name.setText(inf[i]);
                    descr.setText(inf[i+1]);
                    Glide
                            .with(Problems.this)
                            .load(inf[i+3])
                            .into(loadImg);
                    countProblems++;
                    binding.reminderPlace.addView(custom);
                }
            }
        });
        binding.addPurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(countProblems == 30){
                    Toast.makeText(Problems.this, "Вы достигли предела", Toast.LENGTH_SHORT).show();
                }
                else if(binding.name.getText().toString().isEmpty()){
                    Toast.makeText(Problems.this, "Нет названия", Toast.LENGTH_SHORT).show();
                }
                else if(binding.description1.getText().toString().isEmpty()){
                    Toast.makeText(Problems.this, "Нет описания", Toast.LENGTH_SHORT).show();
                }
                else if (mediaPath.isEmpty()) {
                    PostDatas post = new PostDatas();
                    post.postDataCreateProblemWithoutImage("CreateProblemWithoutImage", binding.name.getText().toString(),
                            binding.description1.getText().toString(), prId, mail, new CallBackInt() {
                                @Override
                                public void invoke(String res) {
                                    binding.name.setText("");
                                    binding.description1.setText("");
                                }
                            });
                } else{
                    File file = new File(mediaPath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                    PostDatas post = new PostDatas();
                    post.postDataCreateProblem("CreateProblem", binding.name.getText().toString(), requestBody,
                        binding.description1.getText().toString(), prId, mail, new CallBackInt() {
                            @Override
                            public void invoke(String res) {
                                binding.imagePurp.setImageResource(R.drawable.logo_second_type);
                                binding.name.setText("");
                                binding.description1.setText("");
                            }
                        });
                }
            }
        });

        if(Build.VERSION.SDK_INT >= 33) {
            binding.addPhoto.setOnClickListener(view -> pickImage());
        }
        else{
            binding.addPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(Problems.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Problems.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    } else {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
                        startActivityForResult(Intent.createChooser(intent, "Select Image(s)"), PICK_IMAGES_CODE);
                    }
                }
            });
        }
    }

    private void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image(s)"), PICK_IMAGES_CODE);
            } else {
                Toast.makeText(Problems.this, "You loser", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGES_CODE){

            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                binding.imagePurp.setImageURI(imageUri);
                cursor.close();
                acces = true;
            }

        }
    }

    private void registerResult(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try{
                            Uri imageUri = result.getData().getData();
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};

                            Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                            assert cursor != null;
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            mediaPath = cursor.getString(columnIndex);
                            binding.imagePurp.setImageURI(imageUri);
                            cursor.close();
                            acces = true;
                        }catch (Exception e){
                            Toast.makeText(Problems.this, "LOSER", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}