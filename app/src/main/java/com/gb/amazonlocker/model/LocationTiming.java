package com.gb.amazonlocker.model;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class LocationTiming {
    private Map<DayOfWeek, Timing> timingMap = new HashMap<>();

    // Getter for timingMap
    public Map<DayOfWeek, Timing> getTimingMap() {
        return timingMap;
    }

    // Setter for timingMap
    public void setTimingMap(Map<DayOfWeek, Timing> timingMap) {
        this.timingMap = timingMap;
    }
}
