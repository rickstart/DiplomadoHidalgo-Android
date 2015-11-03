package com.mobintum.movieranking.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobintum.movieranking.R;
import com.mobintum.movieranking.adapters.MovieRVAdapter;
import com.mobintum.movieranking.models.Movie;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ListMoviesFragment extends Fragment implements MovieRVAdapter.OnItemClickListener {
    public final static String TAG = "ListMoviesFragment";

    private OnFragmentInteractionListener mListener;
    private RecyclerView rvMovies;
    private MovieRVAdapter mAdapter;
    private List<Movie> movies = new ArrayList<>();
    public ListMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            new RottenRequest().execute("");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_movies, container, false);
        rvMovies = (RecyclerView) view.findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager( new LinearLayoutManager(getActivity()));
        mAdapter = new MovieRVAdapter(movies,this);
        rvMovies.setAdapter(mAdapter);


        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void itemClick(Movie movie) {
        mListener.onMovieSelected(movie);
    }

    public interface OnFragmentInteractionListener {

        public void onMovieSelected(Movie movie);
    }

    public List<Movie> parseJSONMovie(JSONObject jsonMovies){

        List<Movie> lMovies = new ArrayList<>();


        try {
            JSONArray movies = jsonMovies.getJSONArray("movies");
            for(int i=0; i<movies.length(); i++){
                JSONObject movie = movies.getJSONObject(i);
                String title = movie.getString("title");
                String synopsis = movie.getString("synopsis");
                JSONObject posters = movie.getJSONObject("posters");
                String urlImg = posters.getString("original");
                JSONObject raitings = movie.getJSONObject("ratings");
                int rottenScore = raitings.getInt("critics_score");
                int audienceScore = raitings.getInt("audience_score");

                lMovies.add(new Movie(title,urlImg,synopsis,rottenScore,audienceScore));

            }

            return lMovies;

        }catch (JSONException exception){
            exception.printStackTrace();
            return lMovies;
        }


    }

    public class RottenRequest extends AsyncTask<String,Void,String>{

        ProgressDialog pDialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=35hg37n2zaybbwf7wncj9vgw");
                URL buildURL = new URL(url+"&q=friends&page_limit=50");

                HttpURLConnection conn = (HttpURLConnection) buildURL.openConnection();
                conn.setRequestMethod("GET");
                Log.d(TAG, "Response Code: " + conn.getResponseCode());
                if(conn.getResponseCode() == 200){
                    InputStream in = new BufferedInputStream(conn.getInputStream());
                    String response = IOUtils.toString(in, "UTF-8");
                    Log.d(TAG, response);
                    return response;
                }


                } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
            try {
                JSONObject response = new JSONObject(s);
                movies.clear();
                movies.addAll( parseJSONMovie(response));
                mAdapter.notifyDataSetChanged();

            }catch (JSONException e){
                e.printStackTrace();
            }


        }

    }



}
