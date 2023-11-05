package ru.dvteam.itcollabhub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.nextBut);
        TextView col = findViewById(R.id.collaborotory);
        TextView it = findViewById(R.id.it);
        TextView hub = findViewById(R.id.hub);

        Typeface face=Typeface.createFromAsset(getAssets(),"font/ArchitectsDaughter-Regular.ttf");
        it.setTypeface(face);
        hub.setTypeface(face);
        col.setTypeface(face);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
                String savedText = sPref.getString("UserReg", "");

                Intent intent;

                if(savedText.equals("true")){
                    intent = new Intent(MainActivity.this, MainActivity2.class);
                }
                else {
                    intent = new Intent(MainActivity.this, LogIn.class);
                }

                startActivity(intent);
            }
        });

    }
}
