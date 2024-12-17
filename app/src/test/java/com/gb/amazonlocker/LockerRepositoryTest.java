package com.gb.amazonlocker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.gb.amazonlocker.model.GeoLocation;
import com.gb.amazonlocker.repository.LockerRepository;

public class LockerRepositoryTest {

    @Test
    void testIsNearbyReturnsTrueForCloseLocation() {
        GeoLocation loc1 = new GeoLocation(2434.02, 13742.34);
        GeoLocation loc2 = new GeoLocation(2434.05, 13742.37); // Close location
        double radius = 1.0; // 1 km radius

        boolean result = LockerRepository.isNearby(loc1, loc2, radius);

        assertTrue(result, "Locations should be within the radius.");
    }

    @Test
    void testIsNearbyReturnsFalseForDistantLocation() {
        GeoLocation loc1 = new GeoLocation(2434.02, 13742.34);
        GeoLocation loc2 = new GeoLocation(2500.00, 14500.00); // Distant location
        double radius = 1.0; // 1 km radius

        boolean result = LockerRepository.isNearby(loc1, loc2, radius);

        assertFalse(result, "Locations should not be within the radius.");
    }
}
