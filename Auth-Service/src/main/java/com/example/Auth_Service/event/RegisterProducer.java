package com.example.Auth_Service.event;

import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.Auth_Service.dto.RegisterEvent;


@Service
public class RegisterProducer {
	private final KafkaTemplate<String, RegisterEvent> kafkaTemplate;

    public RegisterProducer(KafkaTemplate<String, RegisterEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    private static final String TOPIC="register-event";
    public void sendEvent(RegisterEvent registerEvent) {
    	kafkaTemplate.send(TOPIC,registerEvent);
    	System.out.println("Register Event Sent Successfully");
    }
}
