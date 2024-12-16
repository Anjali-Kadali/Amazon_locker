package com.gb.amazonlocker.model;

import java.util.ArrayList;
import java.util.List;

public class LockerLocation {
    private String locationId;
    private List<Locker> lockers = new ArrayList<>();
    private GeoLocation geoLocation;
    private LocationTiming locationTiming;

    // Getter for locationId
    public String getLocationId() {
        return locationId;
    }

    // Setter for locationId
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    // Getter for lockers
    public List<Locker> getLockers() {
        return lockers;
    }

    // Setter for lockers
    public void setLockers(List<Locker> lockers) {
        this.lockers = lockers;
    }

    // Getter for geoLocation
    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    // Setter for geoLocation
    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    // Getter for locationTiming
    public LocationTiming getLocationTiming() {
        return locationTiming;
    }

    // Setter for locationTiming
    public void setLocationTiming(LocationTiming locationTiming) {
        this.locationTiming = locationTiming;
    }
}
