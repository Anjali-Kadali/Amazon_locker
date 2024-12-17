package com.gb.amazonlocker.model;


public abstract class Locker {
    private String id;
    private LockerSize lockerSize;
    private String locationId;
    private LockerStatus lockerStatus;
    private GeoLocation geoLocation;

    public Locker(String lockerId,String locationId, LockerSize lockerSize, GeoLocation geoLocation, LockerStatus lockerStatus) {
        this.id = lockerId;
        this.lockerSize = lockerSize;
        this.locationId = locationId;
        this.lockerStatus = lockerStatus;
        this.geoLocation = geoLocation;
    }

    public abstract void reserve();
    public abstract void unlock();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LockerSize getLockerSize() {
        return lockerSize;
    }

    public void setLockerSize(LockerSize lockerSize) {
        this.lockerSize = lockerSize;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public LockerStatus getLockerStatus() {
        return lockerStatus;
    }

    public void setLockerStatus(LockerStatus lockerStatus) {
        this.lockerStatus = lockerStatus;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }
}
