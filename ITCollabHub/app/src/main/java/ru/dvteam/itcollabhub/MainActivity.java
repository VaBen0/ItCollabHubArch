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

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String savedText = sPref.getString("UserReg", "");

        if(savedText.equals("true")){
            Intent intent;
            intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
        else {
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
                    Intent intent;
                    intent = new Intent(MainActivity.this, LogIn.class);
                    startActivity(intent);
                }
            });

        }

    }
}
