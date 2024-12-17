package com.gb.amazonlocker.model;

public class RefrigeratedLocker extends Locker {
    private boolean isCooling;

    public RefrigeratedLocker(String id, LockerSize lockerSize, GeoLocation geoLocation) {
        super(id, null, lockerSize, geoLocation, LockerStatus.AVAILABLE);
        this.isCooling = true;  // Assume it starts cooling
    }

    @Override
    public void reserve() {
        if (getLockerStatus() == LockerStatus.AVAILABLE) {
            setLockerStatus(LockerStatus.BOOKED);
            System.out.println("Refrigerated Locker reserved.");
        } else {
            System.out.println("Refrigerated Locker is already booked.");
        }
    }

    @Override
    public void unlock() {
        if (getLockerStatus() == LockerStatus.BOOKED) {
            setLockerStatus(LockerStatus.AVAILABLE);
            System.out.println("Refrigerated Locker unlocked.");
        } else {
            System.out.println("Refrigerated Locker is not reserved.");
        }
    }

    public void turnOffCooling() {
        if (isCooling) {
            isCooling = false;
            System.out.println("Cooling turned off in Refrigerated Locker.");
        } else {
            System.out.println("Cooling is already off.");
        }
    }
}
