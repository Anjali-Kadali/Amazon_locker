package com.gb.amazonlocker.model;

public class StandardLocker extends Locker {
    public StandardLocker(String id, LockerSize lockerSize, GeoLocation geoLocation) {
        super(id, null, lockerSize, geoLocation, LockerStatus.AVAILABLE);
    }

    @Override
    public void reserve() {
        this.setLockerStatus(LockerStatus.BOOKED);
    }

    @Override
    public void unlock() {
        this.setLockerStatus(LockerStatus.AVAILABLE);
    }
}
