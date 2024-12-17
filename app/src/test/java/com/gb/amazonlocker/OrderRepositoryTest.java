package com.gb.amazonlocker;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.gb.amazonlocker.model.GeoLocation;
import com.gb.amazonlocker.model.Item;
import com.gb.amazonlocker.repository.OrderRepository;

public class OrderRepositoryTest {

    @Test
    void testCreateOrderGeneratesUniqueOrderId() {
        // Given
        Item item1 = new Item();
        item1.setId("item1");
        item1.setQuantity(2);

        Item item2 = new Item();
        item2.setId("item2");
        item2.setQuantity(1);

        List<Item> items = List.of(item1, item2);
        GeoLocation deliveryGeoLocation = new GeoLocation(2434.02, 13742.34);

        // When
        String orderId1 = OrderRepository.createOrder(items, deliveryGeoLocation);
        String orderId2 = OrderRepository.createOrder(items, deliveryGeoLocation);

        // Then
        assertNotNull(orderId1);
        assertNotNull(orderId2);
        assertNotEquals(orderId1, orderId2, "Order IDs should be unique");
    }
}
