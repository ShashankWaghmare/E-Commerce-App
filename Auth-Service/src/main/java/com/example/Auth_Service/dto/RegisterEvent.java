package com.example.Auth_Service.dto;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RegisterEvent {
      private String username;

	  public String getUsername() {
		  return username;
	  }

	  public void setUsername(String username) {
		  this.username = username;
	  }

	  public RegisterEvent(String username) {
		
		this.username = username;
	  }
	  public RegisterEvent() {
	  }
	  
}
