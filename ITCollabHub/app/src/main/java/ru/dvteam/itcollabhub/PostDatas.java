package ru.dvteam.itcollabhub;

import static androidx.core.content.ContextCompat.startActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDatas{
    /*public String getName(){
            return this.name;
        }

        public void setName(String name){
            this.name = name;
        }
        public String getRes(){
            return this.res;
        }

        public void setRes(String res){
            this.res = res;
        }*/
    public String res;
    //private String name;

    public void postDataLogIn(String req, String mail, String pass){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.login(req, mail, pass);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.body().getReturn().equals("Успешный вход")){

                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //setRes("Ошибка сервера");
            }
        });
    }
    public void postDataConfirm(String req, String mail, String code){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.confirm(req, mail, code);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                res = response.body().getReturn();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //setRes("Ошибка сервера");
            }
        });
    }
    public void postDataRegUser(String req, String mail, String pass, String name){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.regEnd(req, mail, pass, name);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                //setRes(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //setRes("Ошибка сервера");
            }
        });
    }
    public void postDataPostCodeMail(String req, String mail){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.postCodeMail(req, mail);

        call.enqueue(new Callback<Model>() {

            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                res = response.body().getReturn();
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //setRes("Ошибка сервера");
            }
        });
    }
}
