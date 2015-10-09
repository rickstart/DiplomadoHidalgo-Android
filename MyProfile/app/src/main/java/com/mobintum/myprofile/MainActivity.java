package com.mobintum.myprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageButton btnEmail;
    private ImageButton btnPhone;
    private ImageButton btnGithub;
    private ImageView imgProfile;
    private TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEmail = (ImageButton) findViewById(R.id.btnEmail);
        btnPhone = (ImageButton) findViewById(R.id.btnPhone);
        btnGithub = (ImageButton) findViewById(R.id.btnGithub);
        imgProfile = (ImageView) findViewById(R.id.imgProfile);
        txtName = (TextView) findViewById(R.id.txtName);

        btnEmail.setOnClickListener(this);

        /*
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, getString(R.string.email), Snackbar.LENGTH_LONG);
                snackbar.setAction("Action", null).show();

            }
        });*/

        btnPhone.setOnClickListener(this);
        /*

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,getString(R.string.phone),Snackbar.LENGTH_LONG).setAction("Action", null).show();


            }
        });*/

        btnGithub.setOnClickListener(this);
        /*
        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,getString(R.string.github),Snackbar.LENGTH_LONG).setAction("Action",null).show();
            }
        });
        */

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgProfile.setImageDrawable(getResources().getDrawable(R.drawable.pic_avatar));
            }
        });

        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtName.setText(getString(R.string.app_name));
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnGithub:
                Snackbar.make(v,getString(R.string.github),Snackbar.LENGTH_LONG).setAction("Action",null).show();
                break;
            case R.id.btnEmail:
                Snackbar.make(v,getString(R.string.email),Snackbar.LENGTH_LONG).setAction("Action",null).show();
                break;
            case R.id.btnPhone:
                Snackbar.make(v,getString(R.string.phone),Snackbar.LENGTH_LONG).setAction("Action",null).show();
                break;
        }

    }
}
