package com.gb.amazonlocker;

import java.util.List;

import com.gb.amazonlocker.model.GeoLocation;
import com.gb.amazonlocker.model.Item;
import com.gb.amazonlocker.model.Locker;
import com.gb.amazonlocker.model.LockerSize;
import com.gb.amazonlocker.repository.OrderRepository;
import com.gb.amazonlocker.service.LockerService;

public class AmazonLockerMain {
    public static void main(String[] args) {
        Item item1 = new Item();
        item1.setId("item1");
        item1.setQuantity(2); // Quantity of item1

        Item item2 = new Item();
        item2.setId("item2");
        item2.setQuantity(1); // Quantity of item2

        List<Item> items = List.of(item1, item2); // List of items in the order
        GeoLocation deliveryGeoLocation = new GeoLocation(2434.02, 13742.34); // Order delivery location

        // Create the order and add it to the repository
        String orderId = OrderRepository.createOrder( items, deliveryGeoLocation);
        System.out.println("Order created: " + orderId);

        // Step 2: Assign a locker based on order's delivery location and item size
        LockerSize lockerSize = LockerSize.M; // Assuming size M for the items
        LockerService lockerService = new LockerService();
        Locker assignedLocker = lockerService.getLocker(lockerSize, deliveryGeoLocation);

        if (assignedLocker != null) {
            System.out.println("Locker assigned: " + assignedLocker.getId());
            System.out.println("Locker location: " + assignedLocker.getGeoLocation());
        } else {
            System.out.println("No locker available for the specified size and location.");
        }
    }
}
