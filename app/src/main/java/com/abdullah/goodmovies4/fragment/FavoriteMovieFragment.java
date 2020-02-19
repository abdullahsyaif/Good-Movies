package com.abdullah.goodmovies4.fragment;


import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.abdullah.goodmovies4.R;
import com.abdullah.goodmovies4.adapter.FavoriteMovieAdapter;
import com.abdullah.goodmovies4.database.FavoriteDatabaseContract;
import com.abdullah.goodmovies4.database.FavMovieHelper;
import com.abdullah.goodmovies4.helper.MappingHelper;
import com.abdullah.goodmovies4.model.FavoriteMovie;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment implements LoadFavMovieCallback{

    private ProgressBar progressBar;
    private RecyclerView rvFavMovie;
    private FavoriteMovieAdapter adapter;

    private FavMovieHelper favMovieHelper;

    private static final String EXTRA_STATE = "EXTRA_STATE";

    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.pb_fav_movie);
        rvFavMovie = view.findViewById(R.id.rv_fav_movie);
        rvFavMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFavMovie.setHasFixedSize(true);
        adapter = new FavoriteMovieAdapter(getActivity());
        rvFavMovie.setAdapter(adapter);

        favMovieHelper = FavMovieHelper.getInstance(getContext());
        favMovieHelper.open();

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        DataObserver myObserver = new DataObserver(handler, getContext());
        getContext().getContentResolver().registerContentObserver(FavoriteDatabaseContract.MovieColumns.CONTENT_URI, true, myObserver);


        if (savedInstanceState == null) {
            // proses ambil data
            new LoadFavMovieAsync(getContext(), this).execute();
        } else {
            ArrayList<FavoriteMovie> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListFavMovie(list);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListFavMovie());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favMovieHelper.close();
    }

    @Override
    public void preExecute() {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void postExecute(ArrayList<FavoriteMovie> favoriteMovies) {
        progressBar.setVisibility(View.INVISIBLE);
        if (favoriteMovies.size() > 0) {
            adapter.setListFavMovie(favoriteMovies);
        } else {
            adapter.setListFavMovie(new ArrayList<FavoriteMovie>());
            showSnackbarMessage("Tidak ada data saat ini");
        }
    }

    private static class LoadFavMovieAsync extends AsyncTask<Void, Void, ArrayList<FavoriteMovie>> {

//        private final WeakReference<FavMovieHelper> weakFavMovieHelper;
//
//        private final WeakReference<LoadFavMovieCallback> weakCallback;

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavMovieCallback> weakCallback;

        private LoadFavMovieAsync(Context context, LoadFavMovieCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }
        @Override
        protected ArrayList<FavoriteMovie> doInBackground(Void... voids) {
//            Cursor dataCursor = weakFavMovieHelper.get().queryAll();
//            return MappingHelper.mapCursorToArrayList(dataCursor);

            Context context = weakContext.get();
            Cursor dataCursor = context.getContentResolver().query(FavoriteDatabaseContract.MovieColumns.CONTENT_URI, null, null, null, null);
            return MappingHelper.mapCursorToArrayList(dataCursor);
        }
        @Override
        protected void onPostExecute(ArrayList<FavoriteMovie> favMovie) {
            super.onPostExecute(favMovie);
            weakCallback.get().postExecute(favMovie);
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(rvFavMovie, message, Snackbar.LENGTH_SHORT).show();
    }

    public static class DataObserver extends ContentObserver {
        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadFavMovieAsync(context, (LoadFavMovieCallback) context).execute();
        }
    }
}

interface LoadFavMovieCallback {
    void preExecute();
    void postExecute(ArrayList<FavoriteMovie> favoriteMovies);
}
