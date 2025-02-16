package com.nexon.nexon.service.impl;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.nexon.nexon.entities.Notification;
import com.nexon.nexon.service.NotificationWebSocketService;

@Service
public class NotificationWebSocketServiceImpl implements NotificationWebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public NotificationWebSocketServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void sendNotification(Long userId, Notification notification) {
        simpMessagingTemplate.convertAndSend("/topic/notifications/" + userId, notification);
    }

}
