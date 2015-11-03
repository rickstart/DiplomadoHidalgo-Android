package com.mobintum.movieranking.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobintum.movieranking.R;
import com.mobintum.movieranking.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rick on 31/10/15.
 */
public class MovieRVAdapter extends RecyclerView.Adapter<MovieRVAdapter.ViewHolder> {

    private List<Movie> movies;
    private OnItemClickListener mListener;
    private Context context;

    public MovieRVAdapter(List<Movie> movies, OnItemClickListener mListener) {
        this.movies = movies;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_movie, parent, false);
        ViewHolder holder = new ViewHolder(view);
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.txtMovieName.setText(movie.getMovieName());
        Picasso.with(context).load(movie.getUrlImage()).into(holder.imgMovie);
        holder.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.itemClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView imgMovie;
        public final TextView txtMovieName;
        public final View itemView;


        public ViewHolder(View itemView) {
            super(itemView);
            this.imgMovie = (ImageView) itemView.findViewById(R.id.imgMovie);
            this.txtMovieName = (TextView) itemView.findViewById(R.id.txtMovieName);
            this.itemView = itemView;
        }

        public void setOnItemClickListener(View.OnClickListener onClickListener){
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    public interface OnItemClickListener{
        public void itemClick(Movie movie);

    }
}
