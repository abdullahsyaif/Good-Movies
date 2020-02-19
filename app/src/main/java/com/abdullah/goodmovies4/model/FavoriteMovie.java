package com.abdullah.goodmovies4.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteMovie implements Parcelable {

    public static final Creator<FavoriteMovie> CREATOR = new Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel in) {
            return new FavoriteMovie(in);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };

    private int id;
    private String vote_count;
    private String poster_path;
    private String id_movie;
    private String title;
    private String vote_average;
    private String overview;
    private String release_date;

    public FavoriteMovie(int id, String vote_count, String poster_path, String id_movie, String title, String vote_average, String overview, String release_date) {
        this.id = id;
        this.vote_count = vote_count;
        this.poster_path = poster_path;
        this.id_movie = id_movie;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;
    }

    protected FavoriteMovie(Parcel in) {
        id = in.readInt();
        vote_count = in.readString();
        poster_path = in.readString();
        id_movie = in.readString();
        title = in.readString();
        vote_average = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    public FavoriteMovie() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getId_movie() {
        return id_movie;
    }

    public void setId_movie(String id_movie) {
        this.id_movie = id_movie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(vote_count);
        parcel.writeString(poster_path);
        parcel.writeString(id_movie);
        parcel.writeString(title);
        parcel.writeString(vote_average);
        parcel.writeString(overview);
        parcel.writeString(release_date);
    }
}