package com.abdullah.goodmovies4.viewmodel;

import android.util.Log;

import com.abdullah.goodmovies4.BuildConfig;
import com.abdullah.goodmovies4.api.ApiClient;
import com.abdullah.goodmovies4.api.ApiInterface;
import com.abdullah.goodmovies4.api.Base;
import com.abdullah.goodmovies4.model.Movie;
import com.abdullah.goodmovies4.model.MovieGet;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private ArrayList<Movie> movieList = new ArrayList<>();
    private MutableLiveData<ArrayList<Movie>> listMovie = new MutableLiveData<>();

    public void setMovies() {
        ApiInterface mApiInterface;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        String API_KEY = BuildConfig.TMDB_API_KEY;

        Call<MovieGet> movieCall = mApiInterface.getMovie(API_KEY, Base.LANGUAGE);
        movieCall.enqueue(new Callback<MovieGet>() {
            @Override
            public void onResponse(Call<MovieGet> call, Response<MovieGet> response) {
                movieList = response.body().getResults();
                Log.d("Retrofit Get Success", "All " + movieList.size());
                listMovie.postValue(movieList);
            }

            @Override
            public void onFailure(Call<MovieGet> call, Throwable t) {
                Log.e("Retrofit Get Failed", t.toString());
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovie;
    }
}
