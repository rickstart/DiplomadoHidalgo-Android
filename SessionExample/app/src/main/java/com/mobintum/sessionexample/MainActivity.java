package com.mobintum.sessionexample;

import android.content.Context;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    SharedPreferences sharedpreferences;
    FragmentManager fm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fm = getSupportFragmentManager();

        sharedpreferences = getSharedPreferences(LoginFragment.MyPREFERENCES, Context.MODE_PRIVATE);

        if(sharedpreferences.contains("session")){
            fm.beginTransaction().replace(R.id.container, new ActiveSessionFragment()).commit();
        }else {
            fm.beginTransaction().replace(R.id.container, new LoginFragment()).commit();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SharedPreferences sharedpreferences = getSharedPreferences(LoginFragment.MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();
            fm.beginTransaction().replace(R.id.container, new LoginFragment()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void authenticationSuccess(boolean success) {
        if(success){
            fm.beginTransaction().replace(R.id.container, new ActiveSessionFragment()).commit();

        }
    }
}
