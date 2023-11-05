package ru.dvteam.itcollabhub;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String savedText = sPref.getString("UserName", "");
        //TextView txt = findViewById(R.id.collaborotory);
        //txt.setText("Добрый вечер, " + savedText + "!");
    }
}