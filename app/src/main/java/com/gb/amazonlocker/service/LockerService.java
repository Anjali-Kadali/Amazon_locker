package com.gb.amazonlocker.service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import com.gb.amazonlocker.exception.LockeCodeMisMatchException;
import com.gb.amazonlocker.exception.LockerNotFoundException;
import com.gb.amazonlocker.exception.PackPickTimeExceededException;
import com.gb.amazonlocker.exception.PickupCodeExpiredException;
import com.gb.amazonlocker.model.GeoLocation;
import com.gb.amazonlocker.model.LocationTiming;
import com.gb.amazonlocker.model.Locker;
import com.gb.amazonlocker.model.LockerLocation;
import com.gb.amazonlocker.model.LockerPackage;
import com.gb.amazonlocker.model.LockerSize;
import com.gb.amazonlocker.model.LockerStatus;
import com.gb.amazonlocker.model.Timing;
import com.gb.amazonlocker.repository.LockerLocationRepository;
import com.gb.amazonlocker.repository.LockerPackageRepository;
import com.gb.amazonlocker.repository.LockerRepository;

public class LockerService {

    public Locker findLockerIbyId(String id) {
        return LockerRepository.lockerMap.get(id);
    }

    public Locker getLocker(LockerSize lockerSize, GeoLocation geoLocation) {
        return checkAndGetAvailableLockers(lockerSize, geoLocation);
    }

    public void pickFromLocker(String lockerId,
                               String code, LocalDateTime localDateTime) throws
            LockerNotFoundException, LockeCodeMisMatchException, PackPickTimeExceededException,
            PickupCodeExpiredException {
        Optional<LockerPackage> lockerPackage =
                LockerPackageRepository.getLockerByLockerId(lockerId);
        if (!lockerPackage.isPresent())
            throw new LockerNotFoundException("Locker with code not found");
        if (!lockerPackage.get().verifyCode(code))
            throw new LockeCodeMisMatchException("Locker code mismatch");
        if (!lockerPackage.get().isValidCode(localDateTime)) {
            throw new PickupCodeExpiredException("Pickup code expired");
        }
        Locker locker = LockerRepository.lockerMap.get(lockerId);
        if (canPickFromLocker(lockerId, localDateTime)) {
            locker.setLockerStatus(LockerStatus.AVAILABLE);
            lockerPackage.get().setCode(null);
        } else {
            lockerPackage.get().setCode(null);
            throw new PackPickTimeExceededException("Package not picked for x days");
        }
    }

    private Locker checkAndGetAvailableLockers(LockerSize lockerSize,   GeoLocation geoLocation) {
        Locker locker = LockerRepository.getLocker(lockerSize, geoLocation);
        if (locker == null) {
            throw new RuntimeException("No available lockers for the specified size and location.");
        }
        locker.setLockerStatus(LockerStatus.BOOKED);
        return locker;
    }

    private boolean canPickFromLocker(String lockerId, LocalDateTime localDateTime) {
        Locker locker = LockerRepository.lockerMap.get(lockerId);
        LockerLocation lockerLocation = LockerLocationRepository.getLockerLocation(locker.getLocationId());
        LocationTiming locationTiming = lockerLocation.getLocationTiming();
        Timing timing = locationTiming.getTimingMap().get(localDateTime.getDayOfWeek());
        Time currentTime = Time.valueOf(getTimeFromDate(localDateTime));
        return currentTime.after(timing.getOpenTime()) && currentTime.before(timing.getCloseTime());
    }

    private String getTimeFromDate(LocalDateTime localDateTime) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = localDateFormat.format(new Date());
        return time;
    }

    
    
}
