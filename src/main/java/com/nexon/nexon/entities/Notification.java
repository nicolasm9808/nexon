package com.nexon.nexon.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "triggered_by_id", nullable = false)
    private User triggeredBy;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "post_id", nullable = true)
    private Post post;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private String message;

    private boolean isRead = false;

    private Date createdAt = new Date();
}
