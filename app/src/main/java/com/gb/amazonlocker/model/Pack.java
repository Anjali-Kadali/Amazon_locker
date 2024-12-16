package com.gb.amazonlocker.model;

import java.util.List;
import java.util.UUID;

public class Pack {
    private String id;
    private double packageSize;
    private String orderId;
    private List<Item> items;

    // Constructor to initialize the Pack
    public Pack(String orderId, List<Item> items) {
        this.id = UUID.randomUUID().toString(); // Generates a random UUID
        this.orderId = orderId;
        this.items = items;
        pack(); // Calls the pack method to calculate the package size
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for packageSize
    public double getPackageSize() {
        return packageSize;
    }

    // Setter for packageSize
    public void setPackageSize(double packageSize) {
        this.packageSize = packageSize;
    }

    // Getter for orderId
    public String getOrderId() {
        return orderId;
    }

    // Setter for orderId
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    // Getter for items
    public List<Item> getItems() {
        return items;
    }

    // Setter for items
    public void setItems(List<Item> items) {
        this.items = items;
    }

    // Private method to calculate the package size
    private void pack() {
        packageSize = Math.random() * 10; // Random size between 0 and 10
    }
}
