package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText user_name = findViewById(R.id.nameu);
        EditText user_pass = findViewById(R.id.passu);
        EditText user_second_pass = findViewById(R.id.passuagain);
        EditText user_mail = findViewById(R.id.mailu);
        TextView col = findViewById(R.id.collaborotory);
        TextView it = findViewById(R.id.it);
        TextView hub = findViewById(R.id.hub);

        Typeface face=Typeface.createFromAsset(getAssets(),"font/ArchitectsDaughter-Regular.ttf");
        it.setTypeface(face);
        hub.setTypeface(face);
        col.setTypeface(face);

        Button conf = findViewById(R.id.Reg);

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_mail.getText().toString().equals("")){
                    user_mail.setHint("Введите вашу почту");
                }
                else if(user_pass.getText().toString().equals("")){
                    user_pass.setHint("Введите пароль");
                }
                else if(user_name.getText().toString().equals("")){
                    user_name.setHint("Введите ваше имя");
                }
                else{
                    if(user_pass.getText().toString().equals(user_second_pass.getText().toString())) {
                        PostDatas post = new PostDatas();
                        post.postDataPostCodeMail("PostToNewUserCode", user_mail.getText().toString(), new CallBackInt() {
                            @Override
                            public void invoke(String res) {
                                Toast.makeText(Register.this, res, Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Register.this, ConfirmReg.class);
                                intent.putExtra("name", user_name.getText().toString());
                                intent.putExtra("pass", user_pass.getText().toString());
                                intent.putExtra("mail", user_mail.getText().toString());
                                startActivity(intent);
                            }
                        });

                    }
                    else{
                        user_second_pass.setHint("Пароли не совпадают");
                        user_second_pass.setText(null);
                    }
                }

            }
        });

    }

    /*public void postData(String mail){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.postCodeMail("PostToNewUserCode", mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.body().getReturn().equals("Код отправлен")) {
                    changeToConf(response.body().getReturn());
                }
                else{
                    Toast.makeText(Register.this, response.body().getReturn(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(Register.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void changeToConf(String res){
        Toast toast = Toast.makeText(this, res, Toast.LENGTH_LONG);
        toast.show();

        EditText user_name = findViewById(R.id.nameu);
        EditText user_pass = findViewById(R.id.passu);
        EditText user_mail = findViewById(R.id.mailu);

        Intent intent = new Intent(Register.this, ConfirmReg.class);
        intent.putExtra("name", user_name.getText().toString());
        intent.putExtra("pass", user_pass.getText().toString());
        intent.putExtra("mail", user_mail.getText().toString());
        startActivity(intent);
    }*/
}
