package com.abdullah.goodmovies4.helper;

import android.database.Cursor;

import com.abdullah.goodmovies4.database.FavoriteDatabaseContract;
import com.abdullah.goodmovies4.model.FavoriteMovie;

import java.util.ArrayList;

public class MappingHelper {

    public static ArrayList<FavoriteMovie> mapCursorToArrayList(Cursor favMovieCursor) {
        ArrayList<FavoriteMovie> favMovieList = new ArrayList<>();

        while (favMovieCursor.moveToNext()) {
            int id = favMovieCursor.getInt(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns._ID));
            String vote_count  = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.VOTE_COUNT));
            String poster_path = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.POSTER_PATH));
            String id_movie = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.ID_MOVIE));
            String title = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.TITLE));
            String vote_average = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.VOTE_AVERAGE));
            String overview = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.OVERVIEW));
            String release_date = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.RELEASE_DATE));

            favMovieList.add(new FavoriteMovie(id, vote_count, poster_path, id_movie, title, vote_average, overview, release_date));
        }
        return favMovieList;
    }

    public static FavoriteMovie mapCursorToObject(Cursor favMovieCursor) {
        favMovieCursor.moveToFirst();
        int id = favMovieCursor.getInt(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns._ID));
        String vote_count  = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.VOTE_COUNT));
        String poster_path = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.POSTER_PATH));
        String id_movie = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.ID_MOVIE));
        String title = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.TITLE));
        String vote_average = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.VOTE_AVERAGE));
        String overview = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.OVERVIEW));
        String release_date = favMovieCursor.getString(favMovieCursor.getColumnIndexOrThrow(FavoriteDatabaseContract.MovieColumns.RELEASE_DATE));

        return new FavoriteMovie(id, vote_count, poster_path, id_movie, title, vote_average, overview, release_date);
    }
}
