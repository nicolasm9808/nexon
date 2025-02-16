package com.nexon.nexon.service.impl;

import java.util.List;

import com.nexon.nexon.entities.Notification;
import com.nexon.nexon.entities.NotificationType;
import com.nexon.nexon.entities.Post;
import com.nexon.nexon.entities.User;
import com.nexon.nexon.repositories.NotificationRepository;
import com.nexon.nexon.repositories.UserRepository;
import com.nexon.nexon.service.NotificationService;

import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createNotification(User recipient, User triggeredBy, NotificationType type, String message, Post post) {
        Notification notification = new Notification();
        notification.setUser(recipient);
        notification.setTriggeredBy(triggeredBy);
        notification.setType(type);
        notification.setMessage(message);
        notification.setPost(post);
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getUserNotifications(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }
}
