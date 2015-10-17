package com.mobintum.musicplayer;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Rick on 17/10/15.
 */
public class Song implements Serializable{
    private String title;
    private String artist;
    private String album;
    private String fileName;
    private String time;
    private int albumImage;

    public Song(String title, String artist, String album, String fileName, String time, int albumImage) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.fileName = fileName;
        this.time = time;
        this.albumImage = albumImage;
    }

    protected Song(Parcel in) {
        title = in.readString();
        artist = in.readString();
        album = in.readString();
        fileName = in.readString();
        time = in.readString();
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(int albumImage) {
        this.albumImage = albumImage;
    }


}
