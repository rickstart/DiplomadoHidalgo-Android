package com.mobintum.movieranking.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.mobintum.movieranking.R;
import com.mobintum.movieranking.fragments.DetailMovieFragment;
import com.mobintum.movieranking.fragments.ListMoviesFragment;
import com.mobintum.movieranking.models.Movie;

public class MainActivity extends AppCompatActivity implements ListMoviesFragment.OnFragmentInteractionListener {
    private FragmentManager fm;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_main,null);
        this.tag = view.getTag().toString();
        setContentView(view);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            if (tag.equals("land")) {
                fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerLeft, new ListMoviesFragment(), ListMoviesFragment.TAG);
                ft.commit();

            }

            if (tag.equals("portrait")) {
                fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, new ListMoviesFragment(), ListMoviesFragment.TAG);
                ft.commit();
            }






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

        if(tag.equals("land")){
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.containerRight, DetailMovieFragment.newInstance(movie), DetailMovieFragment.TAG);
            ft.addToBackStack(ListMoviesFragment.TAG);
            ft.commit();

        }

        if(tag.equals("portrait")){
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.container, DetailMovieFragment.newInstance(movie), DetailMovieFragment.TAG);
            ft.addToBackStack(ListMoviesFragment.TAG);
            ft.commit();

        }




    }
}
