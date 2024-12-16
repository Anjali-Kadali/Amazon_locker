package com.gb.amazonlocker.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gb.amazonlocker.model.GeoLocation;
import com.gb.amazonlocker.model.Item;
import com.gb.amazonlocker.model.Order;

public class OrderRepository {
    public static Map<String, Order> orderMap = new HashMap<>();

    // Fetch an order by ID
    public static Order getOrder(String orderId) {
        return orderMap.get(orderId);
    }

    // Create and add an order to the repository
    public static void createOrder(String orderId, List<Item> items, GeoLocation deliveryGeoLocation) {
        if (orderMap.containsKey(orderId)) {
            throw new IllegalArgumentException("Order ID already exists: " + orderId);
        }
        Order order = new Order();
        order.setOrderId(orderId);
        order.setItems(items);
        order.setDeliveryGeoLocation(deliveryGeoLocation);
        orderMap.put(orderId, order);
    }
}