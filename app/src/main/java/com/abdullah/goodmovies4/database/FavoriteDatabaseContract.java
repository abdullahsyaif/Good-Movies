package com.abdullah.goodmovies4.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class FavoriteDatabaseContract {

    public static final String AUTHORITY = "com.abdullah.goodmovies4";
    private static final String SCHEME = "content";

    public static String TABLE_MOVIE = "favmovie";

    public static final class MovieColumns implements BaseColumns {

        public static String VOTE_COUNT = "vote_count";
        public static String POSTER_PATH = "poster_path";
        public static String ID_MOVIE = "id_movie";
        public static String TITLE = "title";
        public static String VOTE_AVERAGE = "vote_average";
        public static String OVERVIEW = "overview";
        public static String RELEASE_DATE = "release_date";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();

    }
}
