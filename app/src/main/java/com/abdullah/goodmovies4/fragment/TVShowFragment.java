package com.abdullah.goodmovies4.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.abdullah.goodmovies4.R;
import com.abdullah.goodmovies4.activity.DetailActivity;
import com.abdullah.goodmovies4.adapter.TVShowAdapter;
import com.abdullah.goodmovies4.model.TVShow;
import com.abdullah.goodmovies4.viewmodel.TVShowViewModel;

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
public class TVShowFragment extends Fragment {

    private ProgressBar pbTVShow;
    private TVShowAdapter tvShowAdapter;

    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert actionBar != null;
        actionBar.setElevation(4);

        pbTVShow = view.findViewById(R.id.pb_tv_show);

        TVShowViewModel tvShowViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TVShowViewModel.class);

        RecyclerView rvTVShows = view.findViewById(R.id.rv_tv_show);
        rvTVShows.setLayoutManager(new LinearLayoutManager(getContext()));
        tvShowAdapter = new TVShowAdapter();
        tvShowAdapter.notifyDataSetChanged();
        rvTVShows.setAdapter(tvShowAdapter);

        showLoading(true);
        tvShowViewModel.setTVShows();

        tvShowViewModel.getTVShows().observe(Objects.requireNonNull(getActivity()), new Observer<ArrayList<TVShow>>() {
            @Override
            public void onChanged(ArrayList<TVShow> tvShows) {
                if (tvShows != null) {
                    tvShowAdapter.setData(tvShows);
                    showLoading(false);
                }
            }
        });

        tvShowAdapter.setOnItemClickCallback(new TVShowAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(TVShow data) {
                showSelectedTVShow(data);
            }
        });

    }

    private void showSelectedTVShow(TVShow tvShow) {
        Intent tvShowDetailIntent = new Intent(getContext(), DetailActivity.class);
        tvShowDetailIntent.putExtra(DetailActivity.EXTRA_TVSHOW, tvShow);
        startActivity(tvShowDetailIntent);
    }

    private void showLoading(Boolean state) {
        if (state) {
            pbTVShow.setVisibility(View.VISIBLE);
        } else {
            pbTVShow.setVisibility(View.GONE);
        }
    }
}
