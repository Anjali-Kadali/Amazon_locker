package com.gb.amazonlocker.model;

import java.util.List;

public class Order {
    private String orderId;
    private List<Item> items;
    private GeoLocation deliveryGeoLocation;

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

    // Getter for deliveryGeoLocation
    public GeoLocation getDeliveryGeoLocation() {
        return deliveryGeoLocation;
    }

    // Setter for deliveryGeoLocation
    public void setDeliveryGeoLocation(GeoLocation deliveryGeoLocation) {
        this.deliveryGeoLocation = deliveryGeoLocation;
    }
}
