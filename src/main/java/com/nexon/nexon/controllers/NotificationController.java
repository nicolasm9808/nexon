package com.nexon.nexon.controllers;

import com.nexon.nexon.entities.Notification;
import com.nexon.nexon.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/api/notifications")
    public ResponseEntity<List<Notification>> getUserNotifications(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(notificationService.getUserNotifications(username));
    }

    @MessageMapping("/send-notification")
    @SendTo("/topic/notifications")
    public Notification sendNotification(Notification notification) {
        return notification;
    }
}
