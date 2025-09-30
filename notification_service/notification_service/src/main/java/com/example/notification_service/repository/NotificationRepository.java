package com.example.notification_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notification_service.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

}
