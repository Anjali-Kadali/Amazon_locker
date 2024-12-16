package com.gb.amazonlocker.model;

import java.sql.Time;

public class Timing {
    private Time openTime;
    private Time closeTime;

    // Constructor to initialize openTime and closeTime
    public Timing(String openTime, String closeTime) {
        this.openTime = Time.valueOf(openTime); // Converts string to Time object
        this.closeTime = Time.valueOf(closeTime); // Converts string to Time object
    }

    // Getter for openTime
    public Time getOpenTime() {
        return openTime;
    }

    // Setter for openTime
    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    // Getter for closeTime
    public Time getCloseTime() {
        return closeTime;
    }

    // Setter for closeTime
    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }
}
