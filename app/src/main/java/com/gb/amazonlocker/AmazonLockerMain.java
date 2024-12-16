package com.gb.amazonlocker;

import java.util.List;

import com.gb.amazonlocker.model.GeoLocation;
import com.gb.amazonlocker.model.Item;
import com.gb.amazonlocker.model.Order;
import com.gb.amazonlocker.repository.OrderRepository;

public class AmazonLockerMain {
    public static void main(String[] args) {
        // Step 1: Create Order
        String orderId = "order123";  // Unique order ID
        Item item1 = new Item();
        item1.setId("item1");
        item1.setQuantity(2);  // Quantity of item1

        Item item2 = new Item();
        item2.setId("item2");
        item2.setQuantity(1);  // Quantity of item2

        List<Item> items = List.of(item1, item2);  // List of items in the order
        GeoLocation deliveryGeoLocation = new GeoLocation(2434.02384, 13742.3478);  // Order delivery location

        // Create the order and add it to the repository
        Order order = new Order();
        order.setOrderId(orderId);
        order.setItems(items);
        order.setDeliveryGeoLocation(deliveryGeoLocation);

        OrderRepository.orderMap.put(orderId, order);  // Storing the order in the repository

        System.out.println("Order created: " + orderId);
        
    }
}
