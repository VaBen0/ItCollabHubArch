package ru.dvteam.itcollabhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcyivityRetrofit extends AppCompatActivity {
    APIInterface aPIInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acyivity_retrofit);
        aPIInterface = APIClient.getClient().create(APIInterface.class);
        Button check = findViewById(R.id.check);
        User user = new User("TestRetrofit", 100, 101, 200, 200);
        check.setOnClickListener(v -> {
           Call<User> call = aPIInterface.createUser(user);
           call.enqueue(new Callback<User>() {
               @Override
               public void onResponse(Call<User> call, Response<User> response) {
                   User createdUser = response.body();
                   Toast.makeText(AcyivityRetrofit.this, createdUser.KHP, Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onFailure(Call<User> call, Throwable t) {

               }
           });
        });
    }
}