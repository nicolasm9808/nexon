package com.nexon.nexon.service;

import java.util.List;

import com.nexon.nexon.entities.Notification;
import com.nexon.nexon.entities.NotificationType;
import com.nexon.nexon.entities.Post;
import com.nexon.nexon.entities.User;

public interface NotificationService {
    void createNotification(User recipient, User triggeredBy, NotificationType type, String message, Post post);
    List<Notification> getUserNotifications(String username);
}
