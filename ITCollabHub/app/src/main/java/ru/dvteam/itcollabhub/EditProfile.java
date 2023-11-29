package ru.dvteam.itcollabhub;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class EditProfile extends AppCompatActivity {

    ImageView Img;
    private static final int PICK_IMAGES_CODE = 0;
    private String mediaPath = "";
    private int selectedColor, score;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    ActivityResultLauncher<Intent> resultLauncher;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        registerResult();

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String mail = sPref.getString("UserMail", "");
        String name = sPref.getString("UserName", "");
        score = sPref.getInt("UserScore", 0);
        String imgUrl = sPref.getString("UrlImg", "");

        Img = findViewById(R.id.loadImg);
        EditText UserName = findViewById(R.id.nameu);
        Button btn = findViewById(R.id.save);
        String s = "Ваши очки: " + score;
        ImageView userCircle = findViewById(R.id.userCircle);
        ImageView bguser = findViewById(R.id.bguser);
        TextView UserScore = findViewById(R.id.score);
        ImageView restartLine = findViewById(R.id.restart);
        LinearLayout projectMenu = findViewById(R.id.project_menu);
        LinearLayout profileMenu = findViewById(R.id.profile_menu);
        LinearLayout forumMenu = findViewById(R.id.forum_menu);
        TextView quitProfile = findViewById(R.id.quit);
        TextView aboApp = findViewById(R.id.second_menu);
        TextView links = findViewById(R.id.first_menu);
        UserName.setHint(name);
        UserScore.setText(s);

        Glide
                .with(EditProfile.this)
                .load(imgUrl)
                .into(Img);

        if(score < 100){
            bguser.setBackgroundResource(R.drawable.gradient_blue);
            userCircle.setBackgroundResource(R.drawable.circle_blue);
            UserScore.setTextColor(Color.parseColor("#B20000FF"));
            selectedColor = Color.parseColor("#B20000FF");
            btn.setBackgroundColor(selectedColor);
            getWindow().setStatusBarColor(ContextCompat.getColor(EditProfile.this,R.color.blue));
        }
        else if(score < 300){
            bguser.setBackgroundResource(R.drawable.gradient_green);
            userCircle.setBackgroundResource(R.drawable.circle_green);
            UserScore.setTextColor(Color.parseColor("#B21AFF00"));
            selectedColor = Color.parseColor("#B21AFF00");
            btn.setBackgroundColor(selectedColor);
            getWindow().setStatusBarColor(ContextCompat.getColor(EditProfile.this,R.color.green));
        }
        else if(score < 1000){
            bguser.setBackgroundResource(R.drawable.gradient_brown);
            userCircle.setBackgroundResource(R.drawable.circle_brown);
            UserScore.setTextColor(Color.parseColor("#FFCC7722"));
            selectedColor = Color.parseColor("#FFCC7722");
            btn.setBackgroundColor(selectedColor);
            getWindow().setStatusBarColor(ContextCompat.getColor(EditProfile.this,R.color.brown));
        }
        else if(score < 2500){
            bguser.setBackgroundResource(R.drawable.gradient_light_gray);
            userCircle.setBackgroundResource(R.drawable.circle_light_gray);
            UserScore.setTextColor(Color.parseColor("#B2B5B5B5"));
            selectedColor = Color.parseColor("#B2B5B5B5");
            btn.setBackgroundColor(selectedColor);
            getWindow().setStatusBarColor(ContextCompat.getColor(EditProfile.this,R.color.light_gray));
        }
        else if(score < 7000){
            bguser.setBackgroundResource(R.drawable.gradient_ohra);
            userCircle.setBackgroundResource(R.drawable.circle_ohra);
            UserScore.setTextColor(Color.parseColor("#FFE8AA0E"));
            selectedColor = Color.parseColor("#FFE8AA0E");
            btn.setBackgroundColor(selectedColor);
            getWindow().setStatusBarColor(ContextCompat.getColor(EditProfile.this,R.color.ohra));
        }
        else if(score < 17000){
            bguser.setBackgroundResource(R.drawable.gradient_red);
            userCircle.setBackgroundResource(R.drawable.circle_red);
            UserScore.setTextColor(Color.parseColor("#FF0000"));
            selectedColor = Color.parseColor("#FF0000");
            btn.setBackgroundColor(selectedColor);
            getWindow().setStatusBarColor(ContextCompat.getColor(EditProfile.this,R.color.red));
        }
        else if(score < 30000){
            bguser.setBackgroundResource(R.drawable.gradient_orange);
            userCircle.setBackgroundResource(R.drawable.circle_orange);
            UserScore.setTextColor(Color.parseColor("#FFCC7722"));
            selectedColor = Color.parseColor("#FFCC7722");
            btn.setBackgroundColor(selectedColor);
            getWindow().setStatusBarColor(ContextCompat.getColor(EditProfile.this,R.color.orange));
        }
        else if(score < 50000){
            bguser.setBackgroundResource(R.drawable.gradient_violete);
            userCircle.setBackgroundResource(R.drawable.circle_violete);
            UserScore.setTextColor(Color.parseColor("#4F0070"));
            selectedColor = Color.parseColor("#4F0070");
            btn.setBackgroundColor(selectedColor);
            getWindow().setStatusBarColor(ContextCompat.getColor(EditProfile.this,R.color.violete));
        }
        else{
            bguser.setBackgroundResource(R.drawable.gradient_blue_green);
            userCircle.setBackgroundResource(R.drawable.circle_blue_green);
            UserScore.setTextColor(Color.parseColor("#FF00C6A2"));
            selectedColor = Color.parseColor("#FF00C6A2");
            btn.setBackgroundColor(selectedColor);
            getWindow().setStatusBarColor(ContextCompat.getColor(EditProfile.this,R.color.main_green));
        }

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
                Intent intent = new Intent(EditProfile.this, ActivityProject.class);
                startActivity(intent);
            }
        });
        forumMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        quitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this, MainActivity.class);
                SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString("UserReg", "false");
                ed.putString("UserMail", "");
                ed.putInt("UserScore", 0);
                ed.putString("UrlImg", "");
                ed.apply();
                startActivity(intent);
            }
        });

        if(android.os.Build.VERSION.SDK_INT >= 33) {
            Img.setOnClickListener(view -> pickImage());
        }
        else{
            Img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ContextCompat.checkSelfPermission(EditProfile.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(EditProfile.this,
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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText UserName = findViewById(R.id.nameu);
                PostDatas post = new PostDatas();
                if(mediaPath.equals("")){
                    post.postDataEditName("CreateNameLog1", mail, UserName.getText().toString(), new CallBackInt() {
                        @Override
                        public void invoke(String res) {
                            Toast.makeText(EditProfile.this, res, Toast.LENGTH_SHORT).show();
                            if(res.equals("Сохранено")) {
                                Intent intent = new Intent(EditProfile.this, Profile.class);

                                startActivity(intent);
                            }
                        }
                    });
                }
                else if(UserName.getText().toString().equals("")){
                    File file = new File(mediaPath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

                    post.postDataCreateAccount("CreateNameLog1", name, requestBody, mail, new CallBackInt() {
                        @Override
                        public void invoke(String res) {
                            Toast.makeText(EditProfile.this, res, Toast.LENGTH_SHORT).show();
                            if(res.equals("Сохранено")) {
                                Intent intent = new Intent(EditProfile.this, Profile.class);

                                startActivity(intent);
                            }
                        }
                    });
                }
                else{
                    File file = new File(mediaPath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

                    post.postDataCreateAccount("CreateNameLog1", UserName.getText().toString(), requestBody, mail, new CallBackInt() {
                        @Override
                        public void invoke(String res) {
                            Toast.makeText(EditProfile.this, res, Toast.LENGTH_SHORT).show();
                            if(res.equals("Сохранено")) {
                                Intent intent = new Intent(EditProfile.this, Profile.class);

                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(EditProfile.this, R.id.nav_host_fragment);
                navController.navigate(R.id.accountLinks);
            }
        });

        aboApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController = Navigation.findNavController(EditProfile.this, R.id.nav_host_fragment);
                navController.navigate(R.id.aboutApp);
            }
        });
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
                Toast.makeText(EditProfile.this, "You loser", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Button btn = findViewById(R.id.saveBut);
        if (requestCode == PICK_IMAGES_CODE){

            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
                Img.setImageURI(imageUri);
                cursor.close();
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
                            Img.setImageURI(imageUri);
                            cursor.close();
                        }catch (Exception e){
                            Toast.makeText(EditProfile.this, "LOSER", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}