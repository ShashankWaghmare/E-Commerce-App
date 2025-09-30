package com.example.notification_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notification_service.dto.NotificationRequest;
import com.example.notification_service.dto.NotificationResponse;
import com.example.notification_service.service.NotificationService;

@RestController
@RequestMapping("api/notifications/")
public class NotificationController {
	private final NotificationService  notificationService;
	        public NotificationController(NotificationService notificationService) {
	        	this.notificationService=notificationService;
	        }
	
	
	   @PostMapping
	    public NotificationResponse sendNotification(@RequestBody NotificationRequest request) {
	        return notificationService.sendNotification(request);
	    }
}
