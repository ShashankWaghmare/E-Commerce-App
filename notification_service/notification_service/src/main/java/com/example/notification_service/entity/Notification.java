package com.example.notification_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Notification {
	
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
      private long id;
      private String recipient;
      private String message;
      private String subject;
      private String type;
      private String status;
      private LocalDateTime createdAt;
      
}
