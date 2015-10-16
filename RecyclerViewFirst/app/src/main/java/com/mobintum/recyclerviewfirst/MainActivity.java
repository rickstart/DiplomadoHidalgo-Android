package com.mobintum.recyclerviewfirst;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvSimple;
    private RecyclerView.LayoutManager layoutManager;
    private List<Animal> animals;
    private SimpleRVAdapter mAdapter;
    private boolean isGrid = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvSimple = (RecyclerView) findViewById(R.id.rvSimple);
        layoutManager = new LinearLayoutManager(this);
        rvSimple.setLayoutManager(layoutManager);
        rvSimple.setHasFixedSize(true);
        //String[] animals = {"León", "Leopardo", "Jirafa", "Gorila", "Zebra", "Elefante", "Serpiente"};
        animals = new ArrayList<>();
        animals.add(new Animal("León", getResources().getDrawable(R.drawable.pic_leon)));
        animals.add(new Animal("Jirafa", getResources().getDrawable(R.drawable.pic_jirafa)));
        animals.add(new Animal("Gorila", getResources().getDrawable(R.drawable.pic_gorila)));
        animals.add(new Animal("Zebra", getResources().getDrawable(R.drawable.pic_zebra)));
        animals.add(new Animal("Elefante", getResources().getDrawable(R.drawable.pic_elefante)));
        animals.add(new Animal("Serpiente", getResources().getDrawable(R.drawable.pic_serpiente)));



        mAdapter = new SimpleRVAdapter(animals);
        rvSimple.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //         .setAction("Action", null).show();

                if(!isGrid) {
                    layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    rvSimple.setLayoutManager(layoutManager);
                    isGrid = true;
                }else{
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    rvSimple.setLayoutManager(layoutManager);
                    isGrid = false;

                }

            }
            });

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
