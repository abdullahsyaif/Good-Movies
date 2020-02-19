package com.abdullah.goodmovies4.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.abdullah.goodmovies4.R;
import com.abdullah.goodmovies4.activity.DetailActivity;
import com.abdullah.goodmovies4.adapter.MovieAdapter;
import com.abdullah.goodmovies4.model.Movie;
import com.abdullah.goodmovies4.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private ProgressBar pbMovie;
    private MovieAdapter movieAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert actionBar != null;
        actionBar.setElevation(4);

        pbMovie = view.findViewById(R.id.pb_movie);
        MovieViewModel movieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieViewModel.class);

        RecyclerView rvMovies = view.findViewById(R.id.rv_movie);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        movieAdapter = new MovieAdapter();
        movieAdapter.notifyDataSetChanged();
        rvMovies.setAdapter(movieAdapter);

        showLoading(true);
        movieViewModel.setMovies();

        movieViewModel.getMovies().observe(Objects.requireNonNull(getActivity()), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                if (movies != null) {
                    movieAdapter.setData(movies);
                    showLoading(false);
                }
            }
        });

        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                showSelectedMovie(data);
            }
        });
    }


    private void showSelectedMovie(Movie movie) {
        Intent movieDetailIntent = new Intent(getContext(), DetailActivity.class);
        movieDetailIntent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(movieDetailIntent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            pbMovie.setVisibility(View.VISIBLE);
        } else {
            pbMovie.setVisibility(View.GONE);
        }
    }

}
