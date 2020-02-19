package com.abdullah.goodmovies4.viewmodel;

import android.util.Log;

import com.abdullah.goodmovies4.BuildConfig;
import com.abdullah.goodmovies4.api.ApiClient;
import com.abdullah.goodmovies4.api.ApiInterface;
import com.abdullah.goodmovies4.api.Base;
import com.abdullah.goodmovies4.model.TVShow;
import com.abdullah.goodmovies4.model.TVShowGet;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowViewModel extends ViewModel {

    private ArrayList<TVShow> tvshowList = new ArrayList<>();
    private MutableLiveData<ArrayList<TVShow>> listTVShow = new MutableLiveData<>();

    public void setTVShows() {
        ApiInterface mApiInterface;
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        String API_KEY = BuildConfig.TMDB_API_KEY;

        Call<TVShowGet> tvshowCall = mApiInterface.getTVShow(API_KEY, Base.LANGUAGE);
        tvshowCall.enqueue(new Callback<TVShowGet>() {
            @Override
            public void onResponse(Call<TVShowGet> call, Response<TVShowGet> response) {
                tvshowList = response.body().getResults();
                Log.d("Retrofit Get Sukses", "All " + tvshowList.size());
                listTVShow.postValue(tvshowList);
            }

            @Override
            public void onFailure(Call<TVShowGet> call, Throwable t) {
                Log.e("Retrofit Get Gagal", t.toString());
            }
        });
    }

    public LiveData<ArrayList<TVShow>> getTVShows() {
        return listTVShow;
    }
}
