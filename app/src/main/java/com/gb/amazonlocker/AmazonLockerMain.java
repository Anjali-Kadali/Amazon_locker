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
        // Scanner sc = new Scanner(System.in);

        // // Step 1: Take Item Details from User
        // System.out.println("Enter the number of items in the order:");
        // int numItems = sc.nextInt();
        // sc.nextLine();

        // List<Item> items = new java.util.ArrayList<>();
        // for (int i = 0; i < numItems; i++) {
        //     Item item = new Item();
        //     System.out.println("Enter item ID for item " + (i + 1) + ":");
        //     item.setId(sc.nextLine());
        //     System.out.println("Enter quantity for item " + (i + 1) + ":");
        //     item.setQuantity(sc.nextInt());
        //     sc.nextLine();
        //     items.add(item);
        // }

        // // Step 2: Input GeoLocation
        // System.out.println("Enter delivery latitude:");
        // double latitude = sc.nextDouble();
        // System.out.println("Enter delivery longitude:");
        // double longitude = sc.nextDouble();
        // GeoLocation deliveryGeoLocation = new GeoLocation(latitude, longitude);

        // // Step 3: Create Order
        // String orderId = OrderRepository.createOrder(items, deliveryGeoLocation);
        // System.out.println("Order created: " + orderId);
        
        // DeliveryService deliveryService = new DeliveryService();

        // try {
        //     deliveryService.deliver(orderId);
        //     System.out.println("\nDelivery successful! Notification sent to the customer with locker details and access code.");
        // } catch (PackageSizeMappingException e) {
        //     System.out.println("\nDelivery failed: " + e.getMessage());
        // }
        
        // // Step 5: Retrieve Access Code for Customer
        // System.out.println("\nEnter the order ID to retrieve your access code:");
        // String enteredOrderId = sc.next();

        // String accessCode = NotificationRepository.getCodeForOrder(enteredOrderId);
        // if (accessCode != null) {
        //     System.out.println("Your access code is: " + accessCode);
        // } else {
        //     System.out.println("No access code found for the given order ID.");
        // }

        // sc.close();

        // or static input

        Item item1 = new Item();
        item1.setId("item1");
        item1.setQuantity(2);

        Item item2 = new Item();
        item2.setId("item2");
        item2.setQuantity(1);

        List<Item> items = List.of(item1, item2);

        GeoLocation deliveryGeoLocation = new GeoLocation(2434.02, 13742.34);

        String orderId = OrderRepository.createOrder(items, deliveryGeoLocation);
        System.out.println("Order created: " + orderId);

        LockerSize lockerSize = LockerSize.L;
        LockerService lockerService = new LockerService();

        Locker assignedLocker = lockerService.getLocker(lockerSize, deliveryGeoLocation);

        if (assignedLocker != null) {
            System.out.println("Locker assigned: " + assignedLocker.getId());
            System.out.println("Locker location: " + assignedLocker.getGeoLocation().getLatitude()
                    + ", " + assignedLocker.getGeoLocation().getLongitude());
        } else {
            System.out.println("No locker available for the specified size and location.");
        }
    }
}
