package ru.dvteam.itcollabhub;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


public class CreateAccount extends AppCompatActivity {
    ImageView Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Img = findViewById(R.id.loadImg);
        EditText UserName = findViewById(R.id.nameu);
        Button btn = findViewById(R.id.saveBut);

        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadFromGal = new Intent(Intent.ACTION_PICK);
                loadFromGal.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(loadFromGal, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Button btn = findViewById(R.id.saveBut);

        if (resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            Img.setImageURI(selectedImage);
            SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
            String mail = sPref.getString("UserMail", "");

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText UserName = findViewById(R.id.nameu);
                    File file = new File(getRealPathFromURI(selectedImage));

                    RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(selectedImage)), file);
                    PostDatas post = new PostDatas();
                    post.postDataCreateAccount(UserName.getText().toString(), requestFile, mail, new CallBackInt() {
                        @Override
                        public void invoke(String res) {
                            Toast.makeText(CreateAccount.this, res, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CreateAccount.this, MainActivity2.class);
                            startActivity(intent);
                        }
                    });
                }
            });

        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        if( contentUri == null ) {
            return null;
        }
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return contentUri.getPath();
    }
}