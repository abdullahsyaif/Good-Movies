package com.abdullah.goodmovies4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdullah.goodmovies4.R;
import com.abdullah.goodmovies4.api.Base;
import com.abdullah.goodmovies4.model.TVShow;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private OnItemClickCallback onItemClickCallback;

    private ArrayList<TVShow> mData = new ArrayList<>();

    public void setData(ArrayList<TVShow> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowAdapter.TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new TVShowViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TVShowAdapter.TVShowViewHolder holder, int position) {

        holder.bind(mData.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mData.get(holder.getAdapterPosition()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(TVShow data);
    }

    public class TVShowViewHolder extends RecyclerView.ViewHolder {

        TextView tvMovieTitle;
        TextView tvMovieReleaseDate;
        TextView tvMovieRating;
        TextView tvMovieOverview;
        ImageView imgPoster;

        public TVShowViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvMovieReleaseDate = itemView.findViewById(R.id.tv_movie_release_date);
            tvMovieRating = itemView.findViewById(R.id.tv_movie_rating);
            tvMovieOverview = itemView.findViewById(R.id.tv_movie_overview);
            imgPoster = itemView.findViewById(R.id.img_movie_poster);
        }

        void bind(final TVShow tvShow) {
            tvMovieTitle.setText(tvShow.getName());
            tvMovieReleaseDate.setText(tvShow.getFirst_air_date());
            tvMovieRating.setText(tvShow.getVote_average());
            tvMovieOverview.setText(tvShow.getOverview());

            String posterPath = tvShow.getPoster_path();

            String posterURL = Base.IMG_URL + Base.IMG_SIZE + posterPath;

            Glide.with(itemView.getContext())
                    .load(posterURL)
                    .thumbnail(0.1f)
                    .placeholder(R.drawable.img_image_default)
                    .error(R.drawable.img_image_broken)
                    .apply(new RequestOptions().override(342, 278))
                    .into(imgPoster);
        }
    }
}
