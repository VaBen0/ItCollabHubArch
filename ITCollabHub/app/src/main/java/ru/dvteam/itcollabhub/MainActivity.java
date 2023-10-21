package ru.dvteam.itcollabhub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    /*public String getJWT(String key_request, String key_set){

        String secret = "WQqC6WKVLa9mfMolwls1GTmkUIUHBLNE7mMKnWNlk3K1aFklu1";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim(key_request, key_set)
                .setSubject("itcollabhub")
                .signWith(hmacKey)
                .compact();

        return jwtToken;
    }
    EditText name_text;
    EditText mail_text;
    EditText pass_text;
    Button button_super;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Typeface tf = Typeface.createFromAsset(getAssets(),
                "font/ArchitectsDaughter-Regular.ttf");
        TextView tv = (TextView) findViewById(R.id.collaborotory);
        tv.setTypeface(tf);

        name_text = (EditText) findViewById(R.id.nameu);
        mail_text = (EditText) findViewById(R.id.mailu);
        pass_text = (EditText) findViewById(R.id.passu);
        button_super = (Button) findViewById(R.id.but);

        button_super.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://serveritcollabhub.development-team.ru/", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {

                    @Override
                    public Map<String, String> getParams() throws AuthFailureError{
                        Map<String, String> map = new HashMap<>();
                        EditText name = findViewById(R.id.nameu);
                        EditText mail = findViewById(R.id.mailu);
                        EditText pass = findViewById(R.id.passu);

                        map.put("Request", getJWT("Request", "RegNewUser"));
                        map.put("UserName", getJWT("UserName", name.getText().toString()));
                        map.put("UserMail", getJWT("UserMail", mail.getText().toString()));
                        map.put("UserPass", getJWT("UserPass", pass.getText().toString()));

                        return map;
                    }
                };

                requestQueue.add(stringRequest);
            }
        });*/

    }

}
