package com.abdullah.goodmovies4.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TVShow implements Parcelable {
    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
    @SerializedName("original_name")
    private String original_name;
    @SerializedName("genre_ids")
    private ArrayList genre_ids;
    @SerializedName("name")
    private String name;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("origin_country")
    private ArrayList origin_country;
    @SerializedName("vote_count")
    private String vote_count;
    @SerializedName("first_air_date")
    private String first_air_date;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("original_language")
    private String original_language;
    @SerializedName("id")
    private String id;
    @SerializedName("vote_average")
    private String vote_average;
    @SerializedName("overview")
    private String overview;
    @SerializedName("poster_path")
    private String poster_path;

    protected TVShow(Parcel in) {
        original_name = in.readString();
        name = in.readString();
        popularity = in.readString();
        vote_count = in.readString();
        first_air_date = in.readString();
        backdrop_path = in.readString();
        original_language = in.readString();
        id = in.readString();
        vote_average = in.readString();
        overview = in.readString();
        poster_path = in.readString();
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public ArrayList getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public ArrayList getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(ArrayList origin_country) {
        this.origin_country = origin_country;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(original_name);
        parcel.writeString(name);
        parcel.writeString(popularity);
        parcel.writeString(vote_count);
        parcel.writeString(first_air_date);
        parcel.writeString(backdrop_path);
        parcel.writeString(original_language);
        parcel.writeString(id);
        parcel.writeString(vote_average);
        parcel.writeString(overview);
        parcel.writeString(poster_path);
    }
}
