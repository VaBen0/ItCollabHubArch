package ru.dvteam.itcollabhub;

import okhttp3.ResponseBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Methods {

    @FormUrlEncoded
    @POST("/")
    Call<Model> login(@Field("Request")String req, @Field("UserMail")String mail, @Field("UserPassword") String pass);

    @FormUrlEncoded
    @POST("/")
    Call<Model> postCodeMail(@Field("Request")String req, @Field("UserMail")String mail);

    @FormUrlEncoded
    @POST("/")
    Call<Model> getUserInformation(@Field("Request")String req, @Field("UserMail")String mail);

    @FormUrlEncoded
    @POST("/")
    Call<Model> confirm(@Field("Request")String req, @Field("UserMail")String mail, @Field("UserCode")String code);

    @FormUrlEncoded
    @POST("/")
    Call<Model> regEnd(@Field("Request")String req, @Field("UserMail")String mail, @Field("UserPassword")String pass, @Field("UserName")String name);

    @FormUrlEncoded
    @POST("/")
    Call<Model> changeName(@Field("Request")String req, @Field("UserName")String name);


    @Multipart
    @POST("/file_uploads/")
    Call<ResponseBody> uploadImage(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("UserName") RequestBody name);
}