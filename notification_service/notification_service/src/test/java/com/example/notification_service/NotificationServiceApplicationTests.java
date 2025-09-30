package com.example.notification_service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.notification_service.dto.NotificationRequest;
import com.example.notification_service.service.EmailNotificationService;

@SpringBootTest
class NotificationServiceApplicationTests {

	 @Autowired
	private EmailNotificationService email;
	
	
	
	private NotificationRequest request=new NotificationRequest();
	
	
	@Test
	void contextLoads() {
	}
	
	@Test 
	public void SendMail() {
		request.setMessage("Account is registered Successfully");
		request.setSubject("Account Registeration");
		request.setTo("shashankplwwaghmare@gmail.com");
		request.setType("EMAIL");
		email.sendNotification(request);
	}

}
