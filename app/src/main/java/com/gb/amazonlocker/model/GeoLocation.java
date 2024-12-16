package com.gb.amazonlocker.model;

public class GeoLocation {
    private double latitude;
    private double longitude;

    // Constructor
    public GeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter for latitude
    public double getLatitude() {
        return latitude;
    }

    // Setter for latitude
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Getter for longitude
    public double getLongitude() {
        return longitude;
    }

    // Setter for longitude
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
