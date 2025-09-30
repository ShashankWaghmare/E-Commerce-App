package com.example.order_Service.controller;
import com.example.order_Service.model.Order;
import com.example.order_Service.service.OrderService;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import org.springframework.security.core.Authentication;
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
       
	   @Autowired
       private OrderService orderService;
       
	    @PostMapping
	    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
	    	orderService.placeOrder(order);
	    	 return ResponseEntity.ok("Order Placed Suceessfully");
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
	        return ResponseEntity.ok(orderService.getOrder(id));
	    }

	    @GetMapping
	    public ResponseEntity<List<Order>> getAllOrders() {
	        return ResponseEntity.ok(orderService.getAllOrders());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
	        orderService.deleteOrder(id);
	        return ResponseEntity.ok("Order deleted successfully");
	    }
}


