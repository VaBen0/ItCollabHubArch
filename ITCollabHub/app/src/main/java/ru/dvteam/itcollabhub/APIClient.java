package ru.dvteam.itcollabhub;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static Retrofit getClient(){
        OkHttpClient client = new OkHttpClient.Builder().build();

        return new Retrofit.Builder()
                .baseUrl("https://development-team.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
