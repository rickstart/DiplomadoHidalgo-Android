package com.mobintum.soccerfragments;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class DirectorTecnicoActivity extends AppCompatActivity implements View.OnClickListener, Jugador1Fragment.OnFragmentInteractionListener {

    private Button btnPlayer1, btnPlayer2, btnPlayer3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director_tecnico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnPlayer1 = (Button) findViewById(R.id.btnPlayer1);
        btnPlayer2 = (Button) findViewById(R.id.btnPlayer2);
        btnPlayer3 = (Button) findViewById(R.id.btnPlayer3);
        btnPlayer1.setOnClickListener(this);
        btnPlayer2.setOnClickListener(this);
        btnPlayer3.setOnClickListener(this);

        Jugador1Fragment fragment = (Jugador1Fragment) getSupportFragmentManager().findFragmentById(R.id.fragmentPlayer);
        fragment.setInstruccion("Lateral");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_director_tecnico, menu);
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

    @Override
    public void onClick(View v) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (v.getId()){
            case R.id.btnPlayer1:
                ft.add(R.id.delantero, Jugador1Fragment.newInstance("Delatera"), Jugador1Fragment.TAG);
                ft.commit();
                break;
            case R.id.btnPlayer2:
                ft.add(R.id.defensa, Jugador2Fragment.newInstance("Defensa"), Jugador2Fragment.TAG);
                ft.commit();
                break;
            case R.id.btnPlayer3:
                ft.add(R.id.medio, Jugador3Fragment.newInstance("Medio"), Jugador3Fragment.TAG);
                ft.commit();
                break;
        }

    }

    @Override
    public void mensaje(String msg) {
        Jugador1Fragment fragment = (Jugador1Fragment) getSupportFragmentManager().findFragmentByTag(Jugador1Fragment.TAG);
        fragment.setInstruccion(msg);
    }
}
