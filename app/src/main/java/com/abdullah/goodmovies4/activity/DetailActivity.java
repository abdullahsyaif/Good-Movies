package com.abdullah.goodmovies4.activity;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdullah.goodmovies4.R;
import com.abdullah.goodmovies4.api.Base;
import com.abdullah.goodmovies4.database.FavMovieHelper;
import com.abdullah.goodmovies4.model.FavoriteMovie;
import com.abdullah.goodmovies4.model.Movie;
import com.abdullah.goodmovies4.model.TVShow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import static com.abdullah.goodmovies4.database.FavoriteDatabaseContract.MovieColumns.CONTENT_URI;
import static com.abdullah.goodmovies4.database.FavoriteDatabaseContract.MovieColumns.ID_MOVIE;
import static com.abdullah.goodmovies4.database.FavoriteDatabaseContract.MovieColumns.OVERVIEW;
import static com.abdullah.goodmovies4.database.FavoriteDatabaseContract.MovieColumns.POSTER_PATH;
import static com.abdullah.goodmovies4.database.FavoriteDatabaseContract.MovieColumns.RELEASE_DATE;
import static com.abdullah.goodmovies4.database.FavoriteDatabaseContract.MovieColumns.TITLE;
import static com.abdullah.goodmovies4.database.FavoriteDatabaseContract.MovieColumns.VOTE_AVERAGE;
import static com.abdullah.goodmovies4.database.FavoriteDatabaseContract.MovieColumns.VOTE_COUNT;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TVSHOW = "extra_tvshow";

    public static final String EXTRA_FAVMOVIE = "extra_favmovie";
    public static final String EXTRA_POSITION = "extra_position";

    private ProgressBar pbMovie;

    private ImageView imgPoster;
    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvRating;
    private TextView tvOverView;
    private TextView tvRateCount;
    private TextView tvRateAverage;
    private View layoutRate;

    private ImageButton imgBtnFav;
    private ImageButton imgBtnWatchlist;
    private ImageButton imgBtnRate;

    private boolean mFavorite;
    private boolean mWatchlist;
    private boolean mRate;

    private FavoriteMovie favoriteMovie;
    private int position;
    private FavMovieHelper favMovieHelper;

    private Movie movie;

    private Uri uriWithId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.title_detail));

        pbMovie = findViewById(R.id.pb_movie);

        imgPoster = findViewById(R.id.img_movie_poster);
        tvTitle = findViewById(R.id.tv_movie_title);
        tvReleaseDate = findViewById(R.id.tv_movie_release_date);
        tvRating = findViewById(R.id.tv_movie_rating);
        tvOverView = findViewById(R.id.tv_overview);
        tvRateCount = findViewById(R.id.tv_rate_count);
        tvRateAverage = findViewById(R.id.tv_rate_average);

        Button btnPlay = findViewById(R.id.btn_play);
        imgBtnFav = findViewById(R.id.img_btn_favorite);
        imgBtnWatchlist = findViewById(R.id.img_btn_watchlist);
        imgBtnRate = findViewById(R.id.img_btn_rate);

        layoutRate = findViewById(R.id.layout_rate);

        showLoading(true);

        showDetailMovie();
        showDetailTVShow();
        //showDetailFavMovie();


        btnPlay.setOnClickListener(this);
        imgBtnFav.setOnClickListener(this);
        imgBtnWatchlist.setOnClickListener(this);
        imgBtnRate.setOnClickListener(this);

        mFavorite = true;
        mWatchlist = true;
        mRate = true;

        Button btnCoba = findViewById(R.id.btn_coba);
        btnCoba.setOnClickListener(this);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        favMovieHelper = FavMovieHelper.getInstance(this);
        favMovieHelper.open();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favMovieHelper.close();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void showLoading(Boolean state) {
        if (state) {
            pbMovie.setVisibility(View.VISIBLE);
        } else {
            pbMovie.setVisibility(View.GONE);
        }
    }

    private void showDetailMovie() {
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (movie != null) {

            String title = movie.getTitle();
            String releaseDate = movie.getRelease_date();
            String rateCount = movie.getVote_count();
            String rateAverage = movie.getVote_average();
            String posterPath = movie.getPoster_path();
            String overview = movie.getOverview();

            tvTitle.setText(title);
            tvReleaseDate.setText(releaseDate);
            tvRating.setText(rateAverage);
            tvRateCount.setText(rateCount);
            tvRateAverage.setText(rateAverage);
            tvOverView.setText(overview);

            String posterURL = Base.IMG_URL + Base.IMG_SIZE + posterPath;

            Glide.with(this)
                    .load(posterURL)
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.img_image_default)
                    .error(R.drawable.img_image_broken)
                    .apply(new RequestOptions().override(342, 278))
                    .into(imgPoster);

            showLoading(false);
        }
    }

    private void showDetailTVShow() {
        TVShow tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOW);

        if (tvShow != null) {

            String title = tvShow.getName();
            String releaseDate = tvShow.getFirst_air_date();
            String rateCount = tvShow.getVote_count();
            String rateAverage = tvShow.getVote_average();
            String posterPath = tvShow.getPoster_path();
            String overview = tvShow.getOverview();

            tvTitle.setText(title);
            tvReleaseDate.setText(releaseDate);
            tvRating.setText(rateAverage);
            tvRateCount.setText(rateCount);
            tvRateAverage.setText(rateAverage);
            tvOverView.setText(overview);

            String posterURL = Base.IMG_URL + Base.IMG_SIZE + posterPath;

            Glide.with(this)
                    .load(posterURL)
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.img_image_default)
                    .error(R.drawable.img_image_broken)
                    .apply(new RequestOptions().override(342, 278))
                    .into(imgPoster);

            showLoading(false);
        }
    }

    private void showDetailFavMovie() {
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        if (movie != null) {

            String title = movie.getTitle();
            String releaseDate = movie.getRelease_date();
            String rateCount = movie.getVote_count();
            String rateAverage = movie.getVote_average();
            String posterPath = movie.getPoster_path();
            String overview = movie.getOverview();

            tvTitle.setText(title);
            tvReleaseDate.setText(releaseDate);
            tvRating.setText(rateAverage);
            tvRateCount.setText(rateCount);
            tvRateAverage.setText(rateAverage);
            tvOverView.setText(overview);

            String posterURL = Base.IMG_URL + Base.IMG_SIZE + posterPath;

            Glide.with(this)
                    .load(posterURL)
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.img_image_default)
                    .error(R.drawable.img_image_broken)
                    .apply(new RequestOptions().override(342, 278))
                    .into(imgPoster);

            showLoading(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_play:
                toggle(1);
                break;
            case R.id.img_btn_favorite:
                toggle(2);
                break;
            case R.id.img_btn_watchlist:
                toggle(3);
                break;
            case R.id.img_btn_rate:
                toggle(4);
                break;
            case R.id.btn_coba:
                addFavorite();
                break;
        }
    }

    private void toggle(int numbBtn) {
        switch (numbBtn) {
            case 1:
                Toast.makeText(this, getResources().getString(R.string.play_trailer), Toast.LENGTH_SHORT).show();
                break;
            case 2:
                if (mFavorite) {
                    add(numbBtn);
                } else {
                    remove(numbBtn);
                }
                break;
            case 3:
                if (mWatchlist) {
                    add(numbBtn);
                } else {
                    remove(numbBtn);
                }
                break;
            case 4:
                if (mRate) {
                    add(numbBtn);
                } else {
                    remove(numbBtn);
                }
                break;
        }
    }

    private void add(int numbBtn) {
        switch (numbBtn) {
            case 2:
                imgBtnFav.setBackground(getDrawable(R.drawable.ripple_circle_button_accent));
                imgBtnFav.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp));
                Toast.makeText(this, getResources().getString(R.string.add_to_favorites), Toast.LENGTH_SHORT).show();
                mFavorite = false;
                addFavorite();
                break;
            case 3:
                imgBtnWatchlist.setBackground(getDrawable(R.drawable.ripple_circle_button_accent));
                imgBtnWatchlist.setImageDrawable(getDrawable(R.drawable.ic_bookmark_black_24dp));
                Toast.makeText(this, getResources().getString(R.string.add_to_watchlist), Toast.LENGTH_SHORT).show();
                mWatchlist = false;
                break;
            case 4:
                imgBtnRate.setBackground(getDrawable(R.drawable.ripple_circle_button_accent));
                imgBtnRate.setImageDrawable(getDrawable(R.drawable.ic_close_black_24dp));
                layoutRate.setVisibility(View.VISIBLE);
                mRate = false;
                break;
        }
    }

    private void remove(int numbBtn) {
        switch (numbBtn) {
            case 2:
                //deleteFavorite();
                imgBtnFav.setBackground(getDrawable(R.drawable.ripple_circle_button_border));
                imgBtnFav.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_black_24dp));
                Toast.makeText(this, getResources().getString(R.string.remove_from_favorite), Toast.LENGTH_SHORT).show();
                mFavorite = true;
                break;
            case 3:
                imgBtnWatchlist.setBackground(getDrawable(R.drawable.ripple_circle_button_border));
                imgBtnWatchlist.setImageDrawable(getDrawable(R.drawable.ic_bookmark_border_black_24dp));
                Toast.makeText(this, getResources().getString(R.string.remove_from_watchlist), Toast.LENGTH_SHORT).show();
                mWatchlist = true;
                break;
            case 4:
                imgBtnRate.setBackground(getDrawable(R.drawable.ripple_circle_button_border));
                imgBtnRate.setImageDrawable(getDrawable(R.drawable.ic_star_border_black_24dp));
                layoutRate.setVisibility(View.GONE);
                mRate = true;
                break;
        }
    }

    private void deleteFavorite(){
        getContentResolver().delete(uriWithId, null, null);
        Toast.makeText(this, "Berhasil Hapus", Toast.LENGTH_SHORT).show();
    }

    private void addFavorite(){

        if (movie != null) {

            String vote_count = movie.getVote_count();
            String poster_path = movie.getPoster_path();
            String id_movie = movie.getId();
            String title = movie.getTitle();
            String vote_average = movie.getVote_average();
            String overview = movie.getOverview();
            String release_date = movie.getRelease_date();

            ContentValues values = new ContentValues();
            values.put(VOTE_COUNT, vote_count);
            values.put(POSTER_PATH, poster_path);
            values.put(ID_MOVIE, id_movie);
            values.put(TITLE, title);
            values.put(VOTE_AVERAGE, vote_average);
            values.put(OVERVIEW, overview);
            values.put(RELEASE_DATE, release_date);

            getContentResolver().insert(CONTENT_URI, values);
        }
    }

}
