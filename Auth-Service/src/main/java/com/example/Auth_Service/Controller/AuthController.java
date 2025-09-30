package com.example.Auth_Service.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Auth_Service.dto.RegisterEvent;
import com.example.Auth_Service.event.RegisterProducer;
import com.example.Auth_Service.model.User;
import com.example.Auth_Service.repository.UserRepository;
import com.example.Auth_Service.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
	private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
	@Autowired
	private final RegisterProducer registerProducer;
    
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		Optional<User> us=userRepository.findByUsername(user.getUsername());
		if(us.isEmpty()) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		
		RegisterEvent registerEvent = new RegisterEvent();
		registerEvent.setUsername(user.getUsername());
		registerProducer.sendEvent(registerEvent);
		return "User Registered Successfully!";
		}
		else {
		return "User Already Exists. Please Login !";
		}
	}

	@PostMapping("/login")
	public Map<String,String> login(@RequestBody User user){
		  User dbuser=userRepository.findByUsername(user.getUsername()).orElseThrow(()-> new RuntimeException("User Not Found"));

		  if(passwordEncoder.matches(user.getPassword(),dbuser.getPassword())) {
			  String token = jwtUtil.generateToken(dbuser.getUsername());
	           return Map.of("token", token);
		  }
		  else {
			  throw new RuntimeException("Invalid credentials");
		  }

	}

}
