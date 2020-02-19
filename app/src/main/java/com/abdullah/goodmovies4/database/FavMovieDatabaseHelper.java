package com.abdullah.goodmovies4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavMovieDatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbmoviefav";

    private static final int DATABASE_VERSION = 2;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            FavoriteDatabaseContract.TABLE_MOVIE,
            FavoriteDatabaseContract.MovieColumns._ID,
            FavoriteDatabaseContract.MovieColumns.VOTE_COUNT,
            FavoriteDatabaseContract.MovieColumns.POSTER_PATH,
            FavoriteDatabaseContract.MovieColumns.ID_MOVIE,
            FavoriteDatabaseContract.MovieColumns.TITLE,
            FavoriteDatabaseContract.MovieColumns.VOTE_AVERAGE,
            FavoriteDatabaseContract.MovieColumns.OVERVIEW,
            FavoriteDatabaseContract.MovieColumns.RELEASE_DATE
    );

    public FavMovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FavoriteDatabaseContract.TABLE_MOVIE);
        onCreate(sqLiteDatabase);
    }
}
