package com.mobintum.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DetailSongActivity extends AppCompatActivity implements View.OnClickListener, Runnable {

    private ImageButton btnPlayF, btnForwardF, btnBackwardF;
    private ImageView imgThumbDetailF;
    private TextView textDetailSongF, textDetailArtistF, textDetailAlbumF, textDetailTimeF;
    private ProgressBar progressBarF;
    private Song song;
    private MediaPlayer mPlayer;
    private int flag=0;
    private Thread thread;
    int seconds;
    int minutes;
    int hours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_song);

        if(getIntent()!=null){
            song =  (Song) getIntent().getExtras().getSerializable("song");
        }

        btnPlayF = (ImageButton) findViewById(R.id.btnPlayF);
        btnBackwardF = (ImageButton) findViewById(R.id.btnBackwardF);
        btnForwardF = (ImageButton) findViewById(R.id.btnForwardF);

        imgThumbDetailF = (ImageView) findViewById(R.id.imgThumbDetailF);
        textDetailSongF = (TextView) findViewById(R.id.textDetailSongF);
        textDetailArtistF = (TextView) findViewById(R.id.textDetailArtistF);
        textDetailAlbumF = (TextView) findViewById(R.id.textDetailAlbumF);
        textDetailTimeF = (TextView) findViewById(R.id.textDetailTimeF);
        progressBarF = (ProgressBar) findViewById(R.id.progressBarF);

        btnPlayF.setOnClickListener(this);
        btnBackwardF.setOnClickListener(this);
        btnForwardF.setOnClickListener(this);

        thread= new Thread(this);
        loadData(song);

    }

    private void loadData(Song song){

        mPlayer = MediaPlayer.create(
                getApplicationContext(),
                getResources().getIdentifier("raw/"+song.getFileName(),
                        "raw",
                        getPackageName()));

        progressBarF.setVisibility(ProgressBar.VISIBLE);
        progressBarF.setProgress(0);
        progressBarF.setMax(mPlayer.getDuration());


    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnPlayF:


                if(flag==0){

                    mPlayer.start();
                    flag=1;
                    btnPlayF.setImageResource(R.drawable.btn_pause);


                   if(thread.getState()!= Thread.State.TIMED_WAITING)
                        thread.start();

                }else{
                    mPlayer.pause();
                    btnPlayF.setImageResource(R.drawable.btn_play);
                    flag=0;

                }
                break;
            case R.id.btnBackwardF:
                break;
            case R.id.btnForwardF:
                break;
        }



    }


    @Override
    public void run() {
        int currentPosition = 0;
        int total = mPlayer.getDuration();
        while(mPlayer!=null && currentPosition<total){
            try{
                Thread.sleep(1000);
                currentPosition = mPlayer.getCurrentPosition();
                seconds = (currentPosition / 1000) % 60;
                minutes = (currentPosition / (1000 * 60)) % 60;
                hours = (currentPosition / (1000*60*60)) % 60;


                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {
                        if(hours>0){
                            textDetailTimeF.setText(""+hours+":"+minutes+":"+seconds);
                        }else{
                            textDetailTimeF.setText(""+minutes+":"+seconds);
                        }

                    }
                });

                progressBarF.setProgress(currentPosition);

            }catch (Exception e){
                e.printStackTrace();
                return;
            }


        }
    }
}
