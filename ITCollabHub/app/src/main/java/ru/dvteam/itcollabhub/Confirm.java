package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

        Button conf = findViewById(R.id.confirmBut);
        EditText User_code = findViewById(R.id.code);

        String finalMail = mail;
        String finalName = name;
        String finalPass = pass;

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postData(finalMail, User_code.getText().toString(), null, null,  "CheckerCode");
                if(res.equals("Проверка почты прошла успешно")) {
                    postData(finalMail, User_code.getText().toString(), finalName, finalPass, "RegNewUser");
                }
            }
        });
    }

    public void postData(String mail, String code, String pass, String name, String req){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                res = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("Request", req);
                map.put("UserMail", mail);
                map.put("UserPass", pass);
                map.put("UserCode", code);
                map.put("UserName", name);

                return map;
            }
        };

        requestQueue.add(stringRequest);
    }
}