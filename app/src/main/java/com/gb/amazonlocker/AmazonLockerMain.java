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
        // Step 1: Create Items
        Item item1 = new Item();
        item1.setId("item1");
        item1.setQuantity(2); // Quantity of item1

        Item item2 = new Item();
        item2.setId("item2");
        item2.setQuantity(1); // Quantity of item2

        // Combine items into a list
        List<Item> items = List.of(item1, item2);

        // Step 2: Specify the delivery location
        GeoLocation deliveryGeoLocation = new GeoLocation(2434.02, 13742.34);

        // Step 3: Create the order and add it to the repository
        String orderId = OrderRepository.createOrder(items, deliveryGeoLocation);
        System.out.println("Order created: " + orderId);

        // Step 4: Assign a locker
        LockerSize lockerSize = LockerSize.L; // Assume the items need a medium-sized locker
        LockerService lockerService = new LockerService();

        // Use LockerService to find an available locker
        Locker assignedLocker = lockerService.getLocker(lockerSize, deliveryGeoLocation);

        // Step 5: Print locker details or display a failure message
        if (assignedLocker != null) {
            System.out.println("Locker assigned: " + assignedLocker.getId());
            System.out.println("Locker location: " + assignedLocker.getGeoLocation().getLatitude()
                    + ", " + assignedLocker.getGeoLocation().getLongitude());
        } else {
            System.out.println("No locker available for the specified size and location.");
        }
    }
}
