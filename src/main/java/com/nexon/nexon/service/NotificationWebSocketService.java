package com.nexon.nexon.service;
import com.nexon.nexon.entities.Notification;

public interface NotificationWebSocketService {
    void sendNotification(Long userId, Notification notification);
}
