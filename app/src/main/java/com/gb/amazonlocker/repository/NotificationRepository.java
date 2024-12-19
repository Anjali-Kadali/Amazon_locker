package com.gb.amazonlocker.repository;

import java.util.Map;

import com.gb.amazonlocker.model.Notification;

public class NotificationRepository {
    public static Map<String, Notification> notificationMap = new java.util.HashMap<>();

    public static String getCodeForOrder(String orderId) {
        Notification notification = notificationMap.get(orderId);
        if (notification != null) {
            return notification.getCode(); // Return the code
        }
        return null; // Return null if no notification is found
    }
}
