package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText UserMail = findViewById(R.id.mailu);
        EditText UserPass = findViewById(R.id.passu);
        TextView RegBut = findViewById(R.id.regBut);
        TextView ForgotBut = findViewById(R.id.forgotBut);
        Button EnterBut = findViewById(R.id.enterBut);
        TextView col = findViewById(R.id.collaborotory);
        TextView it = findViewById(R.id.it);
        TextView hub = findViewById(R.id.hub);

        Typeface face=Typeface.createFromAsset(getAssets(),"font/ArchitectsDaughter-Regular.ttf");
        it.setTypeface(face);
        hub.setTypeface(face);
        col.setTypeface(face);

        RegBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
            }
        });

        ForgotBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Forgot.class);
                startActivity(intent);
            }
        });

        EnterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserMail.getText().toString().equals("")){
                    UserMail.setHint("Введите ваш логин");
                }
                else if(UserPass.getText().toString().equals("")){
                    UserPass.setHint("Введите пароль");
                }
                else{
                    PostDatas post = new PostDatas();
                    post.postDataLogIn("UserLogIn", UserMail.getText().toString(), UserPass.getText().toString());
                    String res = post.res;
                    UserMail.setText(res);
                    /*SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
                    String savedText = sPref.getString("UserChange", "");
                    Intent intent = new Intent(LogIn.this, MainActivity2.class);
                    if(savedText.equals("true")){
                        startActivity(intent);
                    }*/

                    //Toast.makeText(LogIn.this, res, Toast.LENGTH_SHORT).show();

                    /*if(res.equals("Успешный вход")){
                        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sPref.edit();
                        ed.putString("UserReg", "true");
                        //ed.putString("UserName", post.getName());
                        ed.apply();

                        Intent intent = new Intent(LogIn.this, MainActivity2.class);
                        startActivity(intent);
                    }*/
                }
            }
        });
    }
    public void change(){
        //Toast.makeText(LogIn.this, res, Toast.LENGTH_SHORT).show();

        /*SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("UserReg", "true");
        ed.putString("UserName", name);
        ed.apply();*/

        //Intent intent = new Intent(LogIn.this, MainActivity2.class);
        //startActivity(intent);
    }
}