package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
                    postData(UserMail.getText().toString(), UserPass.getText().toString());
                }
            }
        });
    }

    public void postData(String mail, String pass) {
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.login("UserLogIn", mail, pass);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.body().getReturn().equals("Успешный вход")) {
                    changeToReg(response.body().getReturn(), response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(LogIn.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void changeToReg(String res, String name){
        Toast toast = Toast.makeText(this, res, Toast.LENGTH_LONG);
        toast.show();

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("UserReg", "true");
        ed.putString("UserName", name);
        ed.apply();

        Intent intent = new Intent(LogIn.this, MainActivity2.class);
        startActivity(intent);
    }
}