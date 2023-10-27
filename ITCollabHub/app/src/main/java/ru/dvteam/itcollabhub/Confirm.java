package ru.dvteam.itcollabhub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Confirm extends AppCompatActivity {
    String res = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Bundle arguments = getIntent().getExtras();

        String name = "";
        String mail = "";
        String pass = "";

        if(arguments!=null){
            name = arguments.getString("name");
            mail = arguments.getString("mail");
            pass = arguments.getString("pass");
        }

        TextView enterBut = findViewById(R.id.enterBut);

        enterBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Confirm.this, LogIn.class);
                startActivity(intent);
            }
        });

        Button conf = findViewById(R.id.confirmBut);
        EditText User_code = findViewById(R.id.code);

        String finalMail = mail;
        String finalName = name;
        String finalPass = pass;

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData(finalMail, User_code.getText().toString(), finalPass, finalName);
            }
        });
    }

    public void postData(String mail, String code, String pass, String name){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Проверка почты прошла успешно")){
                    change(mail, code, pass, name, response);
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

                map.put("Request", "CheckerCode");
                map.put("UserMail", mail);
                map.put("UserCode", code);

                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void postData2(String mail, String code, String pass, String name){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Успешная регистрация")){
                    change(mail, code, pass, name, response);
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

                map.put("Request", "RegNewUser");
                map.put("UserMail", mail);
                map.put("UserPass", pass);
                map.put("UserCode", code);
                map.put("UserName", name);

                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void change(String mail, String code, String pass, String name, String res){
        Toast toast = Toast.makeText(this, res, Toast.LENGTH_LONG);
        toast.show();

        EditText User_code = findViewById(R.id.code);
        User_code.setText(res);
        if(res.equals("Проверка почты прошла успешно")){
            postData2(mail, code, pass, name);
        }
        else if(res.equals("Успешная регистрация")){
            SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString("UserReg", "true");
            ed.putString("UserName", name);
            ed.apply();
            Intent intent = new Intent(Confirm.this, MainActivity2.class);
            startActivity(intent);
        }
    }
}