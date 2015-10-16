package com.mobintum.intentssamples;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnCall, btnEmail, btnShare, btnCamera, btnBrowser, btnActivity, btnTwitter, btnWaze, btnStreet, btnEvent;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCall = (ImageButton) findViewById(R.id.btnCall);
        btnEmail = (ImageButton) findViewById(R.id.btnEmail);
        btnShare = (ImageButton) findViewById(R.id.btnShare);
        btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnBrowser = (ImageButton) findViewById(R.id.btnBrowser);
        btnActivity = (ImageButton) findViewById(R.id.btnActivity);
        btnTwitter = (ImageButton) findViewById(R.id.btnTwitter);
        btnWaze = (ImageButton) findViewById(R.id.btnWaze);
        btnStreet = (ImageButton) findViewById(R.id.btnStreet);
        btnEvent = (ImageButton) findViewById(R.id.btnEvent);

        btnCall.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
        btnBrowser.setOnClickListener(this);
        btnActivity.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
        btnWaze.setOnClickListener(this);
        btnStreet.setOnClickListener(this);
        btnEvent.setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnCall:
                intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:(521 5514382887)"));
                startActivity(intent);

                break;

            case R.id.btnEmail:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
                intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
                intent.setData(Uri.parse("mailto:ricardo.celj@gmail.com"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.btnBrowser:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.google.com"));
                startActivity(intent);
                break;

            case R.id.btnCamera:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;

            case R.id.btnShare:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "ETSTSTS");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, "Example text");
                startActivity(intent);
                break;

            case R.id.btnActivity:
                intent = new Intent(this, SecondActivity.class);
                intent.putExtra("title", "Mi segunda Actividad");
                intent.putExtra("content", "Este es el contenido de mi actividad");
                startActivity(intent);

                break;

            case R.id.btnTwitter:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=rickstart"));
                startActivity(intent);

                break;

            case R.id.btnWaze:

                try
                {
                    String url = "waze://?q=Hawaii";
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                    startActivity( intent );
                }
                catch ( ActivityNotFoundException ex  )
                {
                    Intent intent =
                            new Intent( Intent.ACTION_VIEW, Uri.parse( "market://details?id=com.waze" ) );
                    startActivity(intent);
                }

                break;
            case R.id.btnStreet:
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("google.streetview:cbll=19.394999, -99.152504");

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
                break;
            case R.id.btnEvent:

                Calendar cal = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", cal.getTimeInMillis());
                intent.putExtra("allDay", true);
                intent.putExtra("rrule", "FREQ=YEARLY");
                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                intent.putExtra("title", "A Test Event from android app");
                startActivity(intent);

                break;

        }
    }
}
