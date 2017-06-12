package com.example.ramdanariadi.mobileproject.Class;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ramdan ariadi on 09/05/2017.
 */

public class Destination {
    private String picture;
    private int cost;
    private String destinationName;
    private String description;
    private Boolean isFavorite;
    private String categori;
    private String location;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String getCategori() {
        return categori;
    }

    public void setCategori(String categori) {
        this.categori = categori;
    }

    public Destination(String destinationName, String description, int cost, Boolean isFavorite, String picture) {
        this.cost = cost;
        this.description = description;
        this.destinationName = destinationName;
        this.picture = picture;
        this.isFavorite = isFavorite;
    }

    public Destination(String destinationName, String description,int cost, Boolean isFavorite, String location, String picture) {
        this.categori = categori;
        this.cost = cost;
        this.description = description;
        this.destinationName = destinationName;
        this.isFavorite = isFavorite;
        this.location = location;
        this.picture = picture;

    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }
}
