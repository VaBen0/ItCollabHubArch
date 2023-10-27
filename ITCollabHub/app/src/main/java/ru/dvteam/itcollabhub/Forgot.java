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

public class Forgot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        Button conf = findViewById(R.id.confirmBut);
        EditText User_code = findViewById(R.id.code);
        TextView Or_Enter = findViewById(R.id.enterBut);

        Or_Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forgot.this, LogIn.class);
                startActivity(intent);
            }
        });

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData2(User_code.getText().toString());
            }
        });
    }

    public void postData2(String mail){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Код отправлен")){
                    change(mail, response);
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

                map.put("Request", "UserLogInMail");
                map.put("UserMail", mail);

                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void change(String mail, String res){
        Toast toast = Toast.makeText(this, res, Toast.LENGTH_LONG);
        toast.show();

        Intent intent = new Intent(Forgot.this, confirm_forgot_pass.class);
        intent.putExtra("mail", mail);
        startActivity(intent);
    }
}
