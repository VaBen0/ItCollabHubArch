package ru.dvteam.itcollabhub;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import ru.dvteam.itcollabhub.databinding.ActivityProjectFilesBinding;


public class ProjectFiles extends AppCompatActivity {

    private static final int PICK_IMAGES_CODE = 0;
    private String mediaPath = "";
    private Boolean acces = false, clicked = false;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    ActivityResultLauncher<Intent> resultLauncher;

    ActivityProjectFilesBinding binding;
    private String prId, projectTitle, photoProject, mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        mail = sPref.getString("UserMail", "");
        int score = sPref.getInt("UserScore", 0);

        binding = ActivityProjectFilesBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        registerResult();

        Bundle arguments = getIntent().getExtras();

        assert arguments != null;
        prId = arguments.getString("projectId1");
        projectTitle = arguments.getString("projectTitle");
        photoProject = arguments.getString("projectUrlPhoto");

        binding.nameProject.setText(projectTitle);

        Glide
                .with(ProjectFiles.this)
                .load(photoProject)
                .into(binding.prLogo);

        Glide
                .with(ProjectFiles.this)
                .load(photoProject)
                .into(binding.imagePurp);

        PostDatas post = new PostDatas();

        getIds();

        binding.addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.fileName.getText().toString().isEmpty()){
                    Toast.makeText(ProjectFiles.this, "Нет названия", Toast.LENGTH_SHORT).show();
                } else if(binding.fileLink.getText().toString().isEmpty()){
                    Toast.makeText(ProjectFiles.this, "Нет ссылки", Toast.LENGTH_SHORT).show();
                } else if(mediaPath.isEmpty()){
                    post.postDataCreateFileWithoutImage("CreateFileWithoutImage", binding.fileName.getText().toString(),
                            binding.fileLink.getText().toString(), prId, mail, new CallBackInt() {
                                @Override
                                public void invoke(String res) {
                                    binding.fileName.setHint("");
                                    binding.fileLink.setHint("");
                                    binding.filesPlace.removeAllViews();
                                    getIds();
                                }
                            });
                } else{
                    File file = new File(mediaPath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
                    post.postDataCreateFile("CreateFile", binding.fileName.getText().toString(), requestBody,
                            binding.fileLink.getText().toString(), prId, mail, new CallBackInt() {
                                @Override
                                public void invoke(String res) {
                                    binding.fileName.setHint("");
                                    binding.fileLink.setHint("");
                                    Glide
                                            .with(ProjectFiles.this)
                                            .load(photoProject)
                                            .into(binding.imagePurp);
                                    binding.filesPlace.removeAllViews();
                                    getIds();
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
                    if (ContextCompat.checkSelfPermission(ProjectFiles.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ProjectFiles.this,
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
                Toast.makeText(ProjectFiles.this, "You loser", Toast.LENGTH_LONG).show();
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
                            //imageUri.getPath();
                            Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                            assert cursor != null;
                            cursor.moveToFirst();

                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            mediaPath = cursor.getString(columnIndex);
                            binding.imagePurp.setImageURI(imageUri);
                            cursor.close();
                            acces = true;
                        }catch (Exception e){
                            Toast.makeText(ProjectFiles.this, "LOSER", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
    private void getFiles(String filesId){
        PostDatas post = new PostDatas();
        post.postDataGetProjectFiles("GetProjectFiles", filesId, new CallBackInt() {
            @Override
            public void invoke(String res) {
                //Toast.makeText(ProjectFiles.this, res, Toast.LENGTH_SHORT).show();
                String[] inf = res.split("\uD83D\uDD70");
                String[] idm = filesId.split(",");
                for(int i = 0; i < inf.length; i += 4){
                    //Toast.makeText(ProjectFiles.this, inf[i] + " " + inf[i + 2] + " " + inf[i + 3], Toast.LENGTH_SHORT).show();
                    View custom = getLayoutInflater().inflate(R.layout.gfile_panel, null);
                    ImageView loadImg = custom.findViewById(R.id.imagePurp);
                    TextView name = custom.findViewById(R.id.fileName);
                    View back = custom.findViewById(R.id.backGround);
                    ImageView editBut = custom.findViewById(R.id.editBut);
                    ImageView deleteBut = custom.findViewById(R.id.deleteBut);
                    ImageView zakrepBut = custom.findViewById(R.id.zakrepBut);
                    //ImageView editBut = custom.findViewById(R.id.editProblem);

                    name.setText(inf[i]);

                    Glide
                            .with(ProjectFiles.this)
                            .load(inf[i+3])
                            .into(loadImg);

                    if(inf[i + 2].equals("1")){
                        back.setBackgroundResource(R.drawable.violete_transperent);
                    }
                    int finalI = i;

                    custom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            goToLink(inf[finalI + 1]);
                        }
                    });

                    zakrepBut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PostDatas postDatas = new PostDatas();
                            if(inf[finalI + 2].equals("1")){
                                postDatas.postDataDetachFile("DetachedFile", prId, mail, idm[finalI / 4], new CallBackInt() {
                                    @Override
                                    public void invoke(String res) {
                                        back.setBackgroundResource(R.drawable.progress_panel_background);
                                    }
                                });
                            } else{
                                postDatas.postDataFixFile("FixFile", prId, mail, idm[finalI / 4], new CallBackInt() {
                                    @Override
                                    public void invoke(String res) {
                                        binding.filesPlace.removeView(custom);
                                        back.setBackgroundResource(R.drawable.violete_transperent);
                                        binding.filesPlace.addView(custom, 0);
                                    }
                                });
                            }
                        }
                    });

                    editBut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ProjectFiles.this, EditFile.class);
                            intent.putExtra("filePhoto", inf[finalI+3]);
                            intent.putExtra("projectTitle", projectTitle);
                            intent.putExtra("projectUrlPhoto", photoProject);
                            intent.putExtra("projectId1", prId);
                            intent.putExtra("fileName", inf[finalI]);
                            intent.putExtra("fileLink", inf[finalI + 1]);
                            intent.putExtra("fileId", idm[finalI / 4]);
                            startActivity(intent);
                        }
                    });

                    deleteBut.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PostDatas postDatas = new PostDatas();
                            postDatas.postDataDeleteFile("DeleteFile", prId, mail, idm[finalI / 4], new CallBackInt() {
                                @Override
                                public void invoke(String res) {
                                    binding.filesPlace.removeView(custom);
                                }
                            });
                        }
                    });

                    if(inf[i + 2].equals("1")){
                        binding.filesPlace.addView(custom, 0);
                    } else{
                        binding.filesPlace.addView(custom);
                    }
                }
            }
        });
    }
    private void getIds(){
        PostDatas post = new PostDatas();
        post.postDataGetProjectFilesIds("GetProjectFilesIds", prId, new CallBackInt() {
            @Override
            public void invoke(String res) {
                //Toast.makeText(ProjectFiles.this, res, Toast.LENGTH_SHORT).show();
                getFiles(res);
            }
        });
    }

    private void goToLink(String url){
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        binding.filesPlace.removeAllViews();
        getIds();
    }
}