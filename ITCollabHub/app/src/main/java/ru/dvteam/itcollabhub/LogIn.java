package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button conf = findViewById(R.id.enterBut);
        EditText User_mail = findViewById(R.id.mailu);
        EditText User_pass = findViewById(R.id.passu);
        TextView Reg_But = findViewById(R.id.lllllll);

        Reg_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
            }
        });

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData(User_mail.getText().toString(), User_pass.getText().toString());
            }
        });
    }

    public void postData(String mail, String pass){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Код отправлен")){
                    change();
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

                map.put("Request", "EnterInAccount");
                map.put("UserMail", mail);
                map.put("UserPass", pass);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void change(){
        Intent intent = new Intent(LogIn.this, MainActivity2.class);
        startActivity(intent);
    }
}