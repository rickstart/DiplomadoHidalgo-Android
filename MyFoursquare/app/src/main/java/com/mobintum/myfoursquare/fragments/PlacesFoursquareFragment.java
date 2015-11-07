package com.mobintum.myfoursquare.fragments;


import android.app.DownloadManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mobintum.myfoursquare.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFoursquareFragment extends Fragment implements SearchView.OnQueryTextListener, Response.ErrorListener, Response.Listener<String> {

    public static final String TAG = "PlacesFoursquareFragment";
    private final static String URL = "https://api.foursquare.com/v2/venues/search?";



    public PlacesFoursquareFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places_foursquare, container, false);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem menuSearch = menu.findItem(R.id.menuSearch);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuSearch);
        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        query = query.trim().replaceAll(" +", "%20");
        getVenues(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    public void getVenues(String query){

        String buildURL = URL + "client_id="+getString(R.string.four_client_id)+"&client_secret="+getString(R.string.four_client_secret)+"&v=20130815&ll=40.7,-74&query="+query;
        final StringRequest stringRequest = new StringRequest(Request.Method.GET,buildURL,this,this);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        Log.d("DEBUG", response);


    }


}
