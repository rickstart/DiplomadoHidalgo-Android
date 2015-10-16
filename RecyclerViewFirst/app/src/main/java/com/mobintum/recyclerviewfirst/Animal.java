package com.mobintum.recyclerviewfirst;

import android.graphics.drawable.Drawable;

/**
 * Created by Rick on 16/10/15.
 */
public class Animal {

    private String name;
    private String description;
    private Drawable photo;

    public Animal(String name, String description, Drawable photo) {
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public Animal(String name, Drawable photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }
}

