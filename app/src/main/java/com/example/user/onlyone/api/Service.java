package com.example.user.onlyone.api;



import com.example.user.onlyone.Models.Genre;
import com.example.user.onlyone.Models.GenreClass;
import com.example.user.onlyone.Models.TopMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by user on 08-Oct-17.
 */

public interface Service {


    @GET("movie/top_rated")
    Call<TopMovie> getmovies(@Query("api_key") String api);

    @GET("movie/popular")
    Call<TopMovie> getpopularmovies(@Query("api_key") String api);

    @GET("movie/upcoming")
    Call<TopMovie> getupcomingmovies(@Query("api_key") String api);


    @GET("genre/{genre-id}/movies")
    Call<GenreClass> getfiltermovies(@Path("genre-id")ArrayList<Genre> genres, @Query("api_key") String api);
}
