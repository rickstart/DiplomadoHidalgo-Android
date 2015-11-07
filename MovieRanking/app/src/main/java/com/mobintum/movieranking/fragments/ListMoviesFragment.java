package com.mobintum.movieranking.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;

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
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;


public class ListMoviesFragment extends Fragment implements MovieRVAdapter.OnItemClickListener, SearchView.OnQueryTextListener {
    public final static String TAG = "ListMoviesFragment";

    private OnFragmentInteractionListener mListener;
    private RecyclerView rvMovies;
    private MovieRVAdapter mAdapter;
    private List<Movie> movies = new ArrayList<>();
    private MenuItem itemLoading;
    private int totalItems;
    private int page;
    private String queryGlobal;


    public ListMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if(savedInstanceState==null){


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_movies, container, false);
        rvMovies = (RecyclerView) view.findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MovieRVAdapter(movies, this);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_movies, menu);
        final MenuItem itemSearch = menu.findItem(R.id.menuSearch);
        itemLoading = menu.findItem(R.id.menuLoading);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
        searchView.setOnQueryTextListener(this);
        setMenuLoading(false);
    }

    @Override
    public void itemClick(Movie movie) {
        mListener.onMovieSelected(movie);
    }

    @Override
    public void refreshList(int position) {
        if(position<totalItems-1){
            Log.d("DEBUG", "PAGE: "+page+ " query= "+queryGlobal);
            page++;
            Log.d("DEBUG", "PAGE AFTER: "+page);
            String[] params = {queryGlobal,String.valueOf(page) };
            new RottenRequest().execute(params);

        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        query = query.trim().replaceAll(" +", "%20");
        queryGlobal = query;
        page = 1;

        new RottenRequest().execute(query, String.valueOf(page));

        return false;
    }

    private void setMenuLoading(boolean show){

        if(show){
            itemLoading.setVisible(true);
            itemLoading.setActionView(R.layout.menu_loading);
        }else {
            itemLoading.setVisible(false);
        }
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
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



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setMenuLoading(true);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                String query = params[0];
                String page = params[1];
                URL url = new URL("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=35hg37n2zaybbwf7wncj9vgw");
                //String urlStr = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=35hg37n2zaybbwf7wncj9vgw"
                URL buildURL = new URL(url.toString()+"&q="+query+"&page_limit=50&page="+page);
                Log.d(TAG, buildURL.toString());

                HttpURLConnection conn = (HttpURLConnection) buildURL.openConnection();
                conn.setRequestMethod("GET");
                //Log.d(TAG, "Response Code: " + conn.getResponseCode());
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
            setMenuLoading(false);
            try {
                if(s != null) {

                    JSONObject response = new JSONObject(s);
                    totalItems = response.getInt("total");
                    if(page==1) {
                        movies.clear();
                        rvMovies.scrollToPosition(0);
                    }
                    movies.addAll(parseJSONMovie(response));
                    mAdapter.notifyDataSetChanged();
                }

            }catch (JSONException e){
                e.printStackTrace();
            }


        }

    }



}
