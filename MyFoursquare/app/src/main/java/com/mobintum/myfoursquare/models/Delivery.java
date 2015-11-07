package com.mobintum.myfoursquare.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rick on 07/11/15.
 */
public class Delivery {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("provider")
    @Expose
    private Provider provider;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The provider
     */
    public Provider getProvider() {
        return provider;
    }

    /**
     *
     * @param provider
     * The provider
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}