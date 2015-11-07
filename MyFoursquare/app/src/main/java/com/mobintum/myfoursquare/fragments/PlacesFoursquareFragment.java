package com.mobintum.myfoursquare.fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobintum.myfoursquare.R;
import com.mobintum.myfoursquare.models.Location;
import com.mobintum.myfoursquare.models.Venues;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.WeakHashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFoursquareFragment extends Fragment implements
        SearchView.OnQueryTextListener,
        Response.ErrorListener, Response.Listener<String>{

    public static final String TAG = "PlacesFoursquareFragment";
    private final static String URL = "https://api.foursquare.com/v2/venues/search?";
    private GoogleMap gMap;
    private static double lat = 0.0;
    private static double lon =  0.0;
    private HashMap <String, Venues> haspMap = new HashMap<String, Venues>();



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
        final SupportMapFragment map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        gMap = map.getMap();

        gMap.setTrafficEnabled(true);
        gMap.setMyLocationEnabled(true);
        gMap.setBuildingsEnabled(true);
        gMap.isMyLocationEnabled();
        gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if(haspMap.containsKey(marker.getId())){
                    Venues venue = haspMap.get(marker.getId());
                    Snackbar.make(getView(), venue.getUrl(), Snackbar.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        gMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(android.location.Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 13);
                gMap.animateCamera(cameraUpdate);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("It's Me");
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_blue));
                Marker marker = gMap.addMarker(markerOptions);

            }


        });




        return view;
    }


    private boolean checkSelfPermission(String permission) {
        int res = getContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Log.e("DEBUG", "Lat: " + mLastLocation.getLatitude() + " Lon: " + mLastLocation.getLatitude());
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


    public void getVenues(String query) {

        String buildURL = URL + "client_id=" + getString(R.string.four_client_id) + "&client_secret=" + getString(R.string.four_client_secret) + "&v=20130815&ll="+String.valueOf(lat)+","+String.valueOf(lon)+"&query=" + query;
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, buildURL, this, this);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        Log.d("DEBUG", response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject meta = jsonObject.getJSONObject("meta");
            if (meta.getInt("code") == 200) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Venues[] venues = gson.fromJson(jsonObject.getJSONObject("response").getJSONArray("venues").toString(), Venues[].class);
                Log.d("DEBUG", "LENGHT: " + venues.length);
                loadResponse(venues);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void loadResponse(Venues[] venues) {

        LatLng latLng = new LatLng(lat, lon);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 13);
        gMap.animateCamera(cameraUpdate);
        gMap.clear();
        for (Venues venue : venues) {
            MarkerOptions markerOptions = new MarkerOptions();
            Location location = venue.getLocation();
            markerOptions.position(new LatLng(location.getLat(), location.getLng()));
            markerOptions.title(venue.getName());
            Marker marker = gMap.addMarker(markerOptions);
            haspMap.put(marker.getId(), venue);

        }
    }


}
