package com.gb.amazonlocker.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gb.amazonlocker.model.GeoLocation;
import com.gb.amazonlocker.model.Locker;
import com.gb.amazonlocker.model.LockerSize;
import com.gb.amazonlocker.model.LockerStatus;

public class LockerRepository {
    public static List<Locker> lockers;
    public static Map<String, Locker> lockerMap;

    static {
        lockers = new ArrayList<>();
        lockerMap = new HashMap<>();
    
        // Add sample lockers with correct sizes
        Locker locker1 = new Locker("locker1", LockerSize.S, new GeoLocation(2434.02, 13742.34), LockerStatus.AVAILABLE);
        Locker locker2 = new Locker("locker2", LockerSize.M, new GeoLocation(2434.02, 13742.34), LockerStatus.AVAILABLE);
        Locker locker3 = new Locker("locker3", LockerSize.L, new GeoLocation(2434.02, 13742.34), LockerStatus.AVAILABLE);
        Locker locker4 = new Locker("locker4", LockerSize.M, new GeoLocation(1234.56, 7890.12), LockerStatus.AVAILABLE); // Different location
    
        lockers.add(locker1);
        lockers.add(locker2);
        lockers.add(locker3);
        lockers.add(locker4);
    
        // Add lockers to the map
        lockerMap.put(locker1.getId(), locker1);
        lockerMap.put(locker2.getId(), locker2);
        lockerMap.put(locker3.getId(), locker3);
        lockerMap.put(locker4.getId(), locker4);
    }
    

    private static boolean isNearby(GeoLocation loc1, GeoLocation loc2, double radius) {
        double distance = Math.sqrt(
                Math.pow(loc1.getLatitude() - loc2.getLatitude(), 2) +
                Math.pow(loc1.getLongitude() - loc2.getLongitude(), 2)
        );
        return distance <= radius;
    }
    
    public static Locker getLocker(LockerSize lockerSize, GeoLocation location) {
        double searchRadius = 10.0; // Example radius
    
        List<Locker> lockerList =
                lockers.stream()
                        .filter(locker ->
                                locker.getLockerStatus() == LockerStatus.AVAILABLE &&
                                locker.getLockerSize() == lockerSize &&
                                isNearby(locker.getGeoLocation(), location, searchRadius))
                        .collect(Collectors.toList());
    
        if (lockerList != null && !lockerList.isEmpty())
            return lockerList.get(0);
        return null;
    }
    
}
