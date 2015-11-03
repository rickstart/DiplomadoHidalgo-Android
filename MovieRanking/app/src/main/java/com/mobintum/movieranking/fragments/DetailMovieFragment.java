package com.mobintum.movieranking.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobintum.movieranking.R;
import com.mobintum.movieranking.models.Movie;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailMovieFragment extends Fragment {

    public static final String TAG = "DetailMovieFragment";
    private static final String ARG_PARAM_MOVIE = "paramMovie";
    private Movie movie;
    private static TextView txtTitle, txtSynopsis, txtCriticScore, txtAudienceScore;
    private static ImageView imgPoster;
    private static Context context;

    public static DetailMovieFragment newInstance(Movie movie){
        DetailMovieFragment fragment = new DetailMovieFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context= getActivity();

        if(getArguments()!=null)
        this.movie = (Movie) getArguments().getSerializable(ARG_PARAM_MOVIE);
    }

    public DetailMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        txtSynopsis = (TextView) view.findViewById(R.id.txtSynopsis);
        txtCriticScore = (TextView) view.findViewById(R.id.txtCriticScore);
        txtAudienceScore = (TextView) view.findViewById(R.id.txtAudienceScore);
        imgPoster = (ImageView) view.findViewById(R.id.imgPoster);

        if(movie!=null)
        loadMovie(movie);

        return view;
    }


    public static void loadMovie(Movie movie){
        txtTitle.setText(movie.getMovieName());
        txtSynopsis.setText(movie.getSynopsis());
        txtCriticScore.setText(String.valueOf(movie.getTomatometerRank()));
        txtAudienceScore.setText(String.valueOf(movie.getAudienceRank()));
        Picasso.with(context).load(movie.getUrlImage()).into(imgPoster);
    }


}
