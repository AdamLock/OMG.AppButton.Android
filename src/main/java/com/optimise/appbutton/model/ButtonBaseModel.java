package com.optimise.appbutton.model;

/**
 * Created by anoop.singh on 05-May-17.
 */

public class ButtonBaseModel {
    protected double latitude;
    protected double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
