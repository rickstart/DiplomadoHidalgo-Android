package com.mobintum.movieranking.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mobintum.movieranking.R;
import com.mobintum.movieranking.fragments.DetailMovieFragment;
import com.mobintum.movieranking.fragments.ListMoviesFragment;
import com.mobintum.movieranking.models.Movie;

public class MainActivity extends AppCompatActivity implements ListMoviesFragment.OnFragmentInteractionListener {
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new ListMoviesFragment(), ListMoviesFragment.TAG);
        ft.commit();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieSelected(Movie movie) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, DetailMovieFragment.newInstance(movie),DetailMovieFragment.TAG);
        ft.addToBackStack(ListMoviesFragment.TAG);
        ft.commit();

    }
}
