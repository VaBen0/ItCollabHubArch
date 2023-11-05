package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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

public class Forgot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        Button conf = findViewById(R.id.confirmBut);
        EditText User_mail = findViewById(R.id.mailu);
        TextView Or_Enter = findViewById(R.id.enterBut);
        TextView col = findViewById(R.id.collaborotory);
        TextView it = findViewById(R.id.it);
        TextView hub = findViewById(R.id.hub);

        Typeface face=Typeface.createFromAsset(getAssets(),"font/ArchitectsDaughter-Regular.ttf");
        it.setTypeface(face);
        hub.setTypeface(face);
        col.setTypeface(face);

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
                if(User_mail.getText().toString().equals("")){
                    User_mail.setHint("Введите ваш логин");
                }
                else{
                    postData(User_mail.getText().toString());
                }
            }
        });
    }

    public void postData(String mail){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.postCodeMail("UserLogInMail", mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.body().getReturn().equals("Код отправлен")) {
                    changeToConfFor(mail, response.body().getReturn());
                }
                else{
                    Toast.makeText(Forgot.this, response.body().getReturn(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(Forgot.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void changeToConfFor(String mail, String res){
        Toast toast = Toast.makeText(this, res, Toast.LENGTH_LONG);
        toast.show();

        Intent intent = new Intent(Forgot.this, ConfirmForgotPassword.class);
        intent.putExtra("mail", mail);
        startActivity(intent);
    }
}
