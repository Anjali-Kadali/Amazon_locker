package com.gb.amazonlocker.repository;

import java.util.HashMap;
import java.util.Map;

import com.gb.amazonlocker.model.Order;

public class OrderRepository {
    public static Map<String, Order> orderMap = new HashMap<>();

    public static Order getOrder(String orderId) {
        return orderMap.get(orderId);
    }
}
