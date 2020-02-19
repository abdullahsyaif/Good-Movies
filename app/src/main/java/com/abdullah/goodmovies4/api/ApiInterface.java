package com.abdullah.goodmovies4.api;

import com.abdullah.goodmovies4.model.MovieGet;
import com.abdullah.goodmovies4.model.TVShowGet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie")
    Call<MovieGet> getMovie(@Query("api_key") String api_key,
                            @Query("language") String language);

    @GET("tv")
    Call<TVShowGet> getTVShow(@Query("api_key") String api_key,
                              @Query("language") String language);
}
