package com.example.user.onlyone.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 08-Oct-17.
 */

public class Client {
    static final  String BASE_URL = "https://api.themoviedb.org/3/";
    public  static Retrofit retrofit = null;
    public  static  Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
