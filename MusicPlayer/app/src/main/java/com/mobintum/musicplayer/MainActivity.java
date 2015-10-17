package com.mobintum.musicplayer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvSongs;
    private RecyclerView.LayoutManager mLayoutManager;
    private SongRVAdapter mAdapter;
    private List<Song> songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadSongs();
        rvSongs = (RecyclerView) findViewById(R.id.rvSongs);
        mLayoutManager = new LinearLayoutManager(this);
        rvSongs.setHasFixedSize(true);
        rvSongs.setLayoutManager(mLayoutManager);
        mAdapter = new SongRVAdapter(songs);
        rvSongs.setAdapter(mAdapter);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void loadSongs(){
        songs.add(new Song("Photograph","Ed Sheeran","Photograph", "photograph","4:34",getResources().getDrawable(R.drawable.tmb_photograph)));
        songs.add(new Song("Wicked Game","Isaak","Wicked Game", "wicked_game","4:03",getResources().getDrawable(R.drawable.tmb_wicked_game)));
        songs.add(new Song("Lean On","Major Lazer & DJ Snake","Lean On", "lean_on","2:58",getResources().getDrawable(R.drawable.tmb_lean_on)));
        songs.add(new Song("See You Again","Wiz Khalifa ft. Charlie Puth","Fast & Furious 7", "see_you_again","2:58",getResources().getDrawable(R.drawable.tmb_see_you_again)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}