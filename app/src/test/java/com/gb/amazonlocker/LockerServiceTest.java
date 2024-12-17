package com.gb.amazonlocker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.gb.amazonlocker.model.GeoLocation;
import com.gb.amazonlocker.model.Locker;
import com.gb.amazonlocker.model.LockerSize;
import com.gb.amazonlocker.model.LockerStatus;
import com.gb.amazonlocker.service.LockerService;

public class LockerServiceTest {

    @Test
    public void testAssignLockerBasedOnLocationAndSize() {
        LockerService lockerService = new LockerService();
        GeoLocation location = new GeoLocation(2434.02, 13742.34);
        LockerSize size = LockerSize.M;

        Locker assignedLocker = lockerService.getLocker(size, location);

        assertNotNull(assignedLocker, "Locker should be assigned based on size and location");
        assertEquals(size, assignedLocker.getLockerSize(), "Assigned locker size should match");
        assertEquals(LockerStatus.BOOKED, assignedLocker.getLockerStatus(), "Locker status should be booked after assignment");
    }

    @Test
    public void testNoAvailableLockerForLocationAndSize() {
        LockerService lockerService = new LockerService();
        GeoLocation location = new GeoLocation(1234.56, 7890.12); // Location with no lockers
        LockerSize size = LockerSize.L; // Size with no available lockers

        Exception exception = assertThrows(RuntimeException.class, () -> {
            lockerService.getLocker(size, location);
        });

        assertEquals("No available lockers for the specified size and location.", exception.getMessage());
    }
}
