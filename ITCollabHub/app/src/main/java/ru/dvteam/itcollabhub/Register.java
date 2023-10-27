package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;

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

public class Register extends AppCompatActivity {
    String res = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText user_pass = findViewById(R.id.passu);
        EditText user_second_pass = findViewById(R.id.passuagain);
        EditText user_mail = findViewById(R.id.mailu);

        Button conf = findViewById(R.id.Reg);

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_pass.getText().toString().equals(user_second_pass.getText().toString())) {
                    postData(user_mail.getText().toString());
                }
                else{
                    user_second_pass.setHint("Пароли не совпадают");
                    user_second_pass.setText(null);
                    return;
                }
            }
        });

    }

    public void postData(String mail){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Код отправлен")){
                    TextView u = findViewById(R.id.collaborotory);
                    u.setText(response);
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

                map.put("Request", "PostToNewUserCode");
                map.put("UserMail", mail);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void change(){
        EditText user_name = findViewById(R.id.nameu);
        EditText user_pass = findViewById(R.id.passu);
        EditText user_mail = findViewById(R.id.mailu);

        Intent intent = new Intent(Register.this, Confirm.class);
        intent.putExtra("name", user_name.getText().toString());
        intent.putExtra("pass", user_pass.getText().toString());
        intent.putExtra("mail", user_mail.getText().toString());
        startActivity(intent);
    }
}
