package com.gb.amazonlocker.service;

import java.util.List;

import com.gb.amazonlocker.model.Item;
import com.gb.amazonlocker.model.Order;
import com.gb.amazonlocker.repository.OrderRepository;

public class OrderService {


    public Order getOrder(String orderId) {
        return OrderRepository.getOrder(orderId);
    }

    public List<Item> getItemsForOrder(String orderId) {
        return OrderRepository.getOrder(orderId).getItems();
    }

    public void initiateRefund(String orderId) {
        System.out.printf("Refund for order %s initiated", orderId);
    }

}
