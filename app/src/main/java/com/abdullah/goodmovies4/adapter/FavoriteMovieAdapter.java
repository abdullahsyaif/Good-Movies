package com.abdullah.goodmovies4.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdullah.goodmovies4.Listener.CustomOnItemClickListener;
import com.abdullah.goodmovies4.R;
import com.abdullah.goodmovies4.activity.DetailActivity;
import com.abdullah.goodmovies4.api.Base;
import com.abdullah.goodmovies4.model.FavoriteMovie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavMovieViewHolder> {

    private ArrayList<FavoriteMovie> listFavMovie = new ArrayList<>();
    private Activity activity;


    public FavoriteMovieAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<FavoriteMovie> getListFavMovie() {
        return listFavMovie;
    }


    public void addItem(FavoriteMovie favoriteMovie) {
        this.listFavMovie.add(favoriteMovie);
        notifyItemInserted(listFavMovie.size() - 1);
    }
    public void updateItem(int position, FavoriteMovie favoriteMovie) {
        this.listFavMovie.set(position, favoriteMovie);
        notifyItemChanged(position, favoriteMovie);
    }
    public void removeItem(int position) {
        this.listFavMovie.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,listFavMovie.size());
    }

    public void setListFavMovie(ArrayList<FavoriteMovie> listFavMovie) {

        if (listFavMovie.size() > 0) {
            this.listFavMovie.clear();
        }
        this.listFavMovie.addAll(listFavMovie);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteMovieAdapter.FavMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new FavMovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieAdapter.FavMovieViewHolder holder, int position) {
        holder.bind(listFavMovie.get(position));

        holder.itemView.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_POSITION, position);
                intent.putExtra(DetailActivity.EXTRA_FAVMOVIE, listFavMovie.get(position));
                //activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE);
                activity.startActivity(intent);
                //Toast.makeText(activity, "Bisa diklik", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return listFavMovie.size();
    }

    public class FavMovieViewHolder extends RecyclerView.ViewHolder {

        TextView tvMovieTitle;
        TextView tvMovieReleaseDate;
        TextView tvMovieRating;
        TextView tvMovieOverview;
        ImageView imgPoster;

        public FavMovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvMovieReleaseDate = itemView.findViewById(R.id.tv_movie_release_date);
            tvMovieRating = itemView.findViewById(R.id.tv_movie_rating);
            tvMovieOverview = itemView.findViewById(R.id.tv_movie_overview);
            imgPoster = itemView.findViewById(R.id.img_movie_poster);
        }

        void bind(final FavoriteMovie favMovie) {
            tvMovieTitle.setText(favMovie.getTitle());
            tvMovieReleaseDate.setText(favMovie.getRelease_date());
            tvMovieRating.setText(favMovie.getVote_average());
            tvMovieOverview.setText(favMovie.getOverview());

            String posterPath = favMovie.getPoster_path();

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
