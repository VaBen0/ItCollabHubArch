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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class log_in extends AppCompatActivity {

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
                Intent intent = new Intent(log_in.this, Register.class);
                startActivity(intent);
            }
        });

        ForgotBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(log_in.this, Forgot.class);
                startActivity(intent);
            }
        });

        EnterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData(UserMail.getText().toString(), UserPass.getText().toString());
            }
        });
    }

    public void postData(String mail, String pass){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Успешный вход")){
                    change(response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("Request", "UserLogIn");
                map.put("UserMail", mail);
                map.put("UserPassword", pass);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void change(String res){
        Toast toast = Toast.makeText(this, res, Toast.LENGTH_LONG);
        toast.show();

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("UserReg", "true");
        ed.apply();

        Intent intent = new Intent(log_in.this, MainActivity2.class);
        startActivity(intent);
    }
}