package ru.dvteam.itcollabhub;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("development-team.ru/adspost/index.php")
    Call<DataModal>  createPost(@Body DataModal dataModal);
}
