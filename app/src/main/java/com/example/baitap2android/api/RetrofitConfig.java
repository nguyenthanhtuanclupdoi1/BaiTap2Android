package com.example.baitap2android.api;

import com.example.baitap2android.model.Account;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitConfig {
    Gson gson = new GsonBuilder().setLenient().create();
    RetrofitConfig retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.43.144/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RetrofitConfig.class);

    @FormUrlEncoded
    @POST("android/register.php")
    Call<String> register(@Field("username") String username, @Field("password") String password);


    @POST("android/login.php")
    Call<ArrayList<Account>> login();
}
