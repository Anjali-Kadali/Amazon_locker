package com.gb.amazonlocker.model;

import java.time.LocalDateTime;

import com.gb.amazonlocker.exception.PickupCodeExpiredException;

public class LockerPackage {
    private final int codeValidDays = 3;
    private String lockerId;
    private String orderId;
    private String code;
    private LocalDateTime packageDeliveredTime;

    // Getter for codeValidDays
    public int getCodeValidDays() {
        return codeValidDays;
    }

    // Getter for lockerId
    public String getLockerId() {
        return lockerId;
    }

    // Setter for lockerId
    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    // Getter for orderId
    public String getOrderId() {
        return orderId;
    }

    // Setter for orderId
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    // Getter for code
    public String getCode() {
        return code;
    }

    // Setter for code
    public void setCode(String code) {
        this.code = code;
    }

    // Getter for packageDeliveredTime
    public LocalDateTime getPackageDeliveredTime() {
        return packageDeliveredTime;
    }

    // Setter for packageDeliveredTime
    public void setPackageDeliveredTime(LocalDateTime packageDeliveredTime) {
        this.packageDeliveredTime = packageDeliveredTime;
    }

    // Method to check if the code is valid
    public boolean isValidCode(LocalDateTime currentTime) throws PickupCodeExpiredException {
        if (currentTime.isAfter(packageDeliveredTime.plusDays(codeValidDays))) {
            throw new PickupCodeExpiredException("Pickup code expired.");
        }
        return true;
    }

    // Method to verify the code
    public boolean verifyCode(String code) {
        return this.code.equals(code);
    }
}
