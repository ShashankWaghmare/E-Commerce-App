package com.example.notification_service.service;

import com.example.notification_service.dto.NotificationRequest;
import com.example.notification_service.dto.NotificationResponse;

public interface NotificationService {
           public NotificationResponse sendNotification(NotificationRequest request);
}
