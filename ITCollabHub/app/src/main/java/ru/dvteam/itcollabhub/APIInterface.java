package ru.dvteam.itcollabhub;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("/testretrofit/")
    Call<User> createUser(@Body User user);
}
