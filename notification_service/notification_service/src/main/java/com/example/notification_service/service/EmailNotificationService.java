package com.example.notification_service.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.notification_service.dto.NotificationRequest;
import com.example.notification_service.dto.NotificationResponse;


@Service
public class EmailNotificationService implements NotificationService {
     
	
	 @Autowired
	 private JavaMailSender mailSender;
	
	@Override
	public NotificationResponse sendNotification(NotificationRequest request) {
		 NotificationResponse response = new NotificationResponse();
	        try {
	            SimpleMailMessage message = new SimpleMailMessage();
	            message.setTo(request.getTo());
	            message.setSubject(request.getSubject());
	            message.setText(request.getMessage());
	            mailSender.send(message);
                response.setStatus("SENT");
	            response.setTimestamp(LocalDateTime.now().toString());
	        } catch (Exception e) {
	            response.setStatus("FAILED");
	            response.setErrorMessage(e.getMessage());
	        }
	        return response;
	}
	 

}
