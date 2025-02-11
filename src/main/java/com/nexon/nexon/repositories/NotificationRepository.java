package com.nexon.nexon.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexon.nexon.entities.Notification;
import com.nexon.nexon.entities.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndRead(User user, Boolean read);
}