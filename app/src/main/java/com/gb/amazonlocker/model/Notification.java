package com.gb.amazonlocker.model;

public class Notification {
    private String customerId;
    private String orderId;
    private String lockerId;
    private String code;

    // Constructor
    public Notification(String customerId, String orderId, String lockerId, String code) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.lockerId = lockerId;
        this.code = code;
    }

    // Getter for customerId
    public String getCustomerId() {
        return customerId;
    }

    // Getter for orderId
    public String getOrderId() {
        return orderId;
    }

    // Getter for lockerId
    public String getLockerId() {
        return lockerId;
    }

    // Getter for code
    public String getCode() {
        return code;
    }
}
