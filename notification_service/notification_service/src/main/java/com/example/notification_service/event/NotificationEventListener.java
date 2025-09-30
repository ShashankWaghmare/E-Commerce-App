package com.example.notification_service.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.notification_service.dto.NotificationRequest;
import com.example.notification_service.service.NotificationService;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.criteria.Order;



@Service
public class NotificationEventListener {
           private final NotificationService notificationService;
           
           
           
           public NotificationEventListener(NotificationService notificationService) {
        	   this.notificationService=notificationService;
           }
           
           
           @KafkaListener(topics = "order-events", groupId = "notification-group")
           public void handleOrderEvent(JsonNode event) {
        	   System.out.println(event.asText());
        	  NotificationRequest notificationRequest=new NotificationRequest();
        	  notificationRequest.setTo(event.get("customerEmail").asText());
              notificationRequest.setSubject("Order Confirmation");
              JsonNode orderNode = event.get("order");
              notificationRequest.setType("EMAIL");
              notificationRequest.setMessage("Hello Shashank,\r\n"
              		+ "\r\n"
              		+ "Thank you for shopping with us! 🎉\r\n"
              		+ "We’re happy to let you know that your order #12345 has been placed successfully.\r\n"
              		+ "\r\n"
              		+ "Order Details:\r\n"
              		+ "\r\n"
              		+ "Order ID:+"+event.get("orderId").asText()
              		+ "\r\n"
              		+ "Order Date: 29-Sep-2025\r\n"
              		+ "\r\n"
              		+ "Order Number:"+ orderNode.get("orderNumber").asText()
              	    + "\r\n"
              	    +  "Product Code :"+orderNode.get("productCode").asText()
              	    + "\r\n"
              	    + "Quantity : "+orderNode.get("quantity").asInt()+
              	    "\r\n"
              		 + "You will receive another update once your order is shipped.\r\n"
              		+ "\r\n"
              		+ "👉 You can track your order anytime here: Track Order\r\n"
              		+ "\r\n"
              		+ "Thank you for choosing E-Shop. We look forward to serving you again!\r\n"
              		+ "\r\n"
              		+ "Best regards,\r\n"
              		+ "Team E-Shop");
              notificationService.sendNotification(notificationRequest);
           }
           
           @KafkaListener(topics = "register-event", groupId = "notification-group")
           public void registerEvent(JsonNode event) {
        	  System.out.println(event.toString());
        	  NotificationRequest notificationRequest=new NotificationRequest();
        	  notificationRequest.setTo(event.get("username").asText());
              notificationRequest.setSubject("Welcome to Mahalaxmi Gruhvastu Bhandar! Your Account Has Been Created");
              notificationRequest.setType("EMAIL");
              notificationRequest.setMessage("Hi [User Name],\r\n"
              		+ "\r\n"
              		+ "Welcome to Mahalaxmi Gruhvastu Bhandar! Your account has been successfully created. 🎉\r\n"
              		+ "\r\n"
              		+ "Here are your account details:\r\n"
              		+ "\r\n"
              		+ "Username / Email:"
              		+ "]\r\n"
              		+ "\r\n"
              		+ "You can now log in and start exploring our wide range of products: [Login Link]\r\n"
              		+ "\r\n"
              		+ "Tip: Keep your account details safe and don’t share your password with anyone.\r\n"
              		+ "\r\n"
              		+ "We’re excited to have you on board and hope you enjoy shopping with us!\r\n"
              		+ "\r\n"
              		+ "Best regards,\r\n"
              		+ "The [Your App Name] Team\r\n"
              		+ "[Website URL] | [Support Email] | [Customer Care Number]");
              notificationService.sendNotification(notificationRequest);
           }
}
