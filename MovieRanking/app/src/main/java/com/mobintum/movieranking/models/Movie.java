package com.mobintum.movieranking.models;

import java.io.Serializable;

/**
 * Created by Rick on 31/10/15.
 */
public class Movie implements Serializable {

    private String movieName;
    private String urlImage;
    private String synopsis;
    private int tomatometerRank;
    private int AudienceRank;

    public Movie(String movieName, String urlImage, String synopsis, int tomatometerRank, int audienceRank) {
        this.movieName = movieName;
        this.urlImage = urlImage;
        this.synopsis = synopsis;
        this.tomatometerRank = tomatometerRank;
        AudienceRank = audienceRank;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getTomatometerRank() {
        return tomatometerRank;
    }

    public void setTomatometerRank(int tomatometerRank) {
        this.tomatometerRank = tomatometerRank;
    }

    public int getAudienceRank() {
        return AudienceRank;
    }

    public void setAudienceRank(int audienceRank) {
        AudienceRank = audienceRank;
    }
}
