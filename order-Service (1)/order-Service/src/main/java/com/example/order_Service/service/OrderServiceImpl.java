package com.example.order_Service.service;


import com.example.order_Service.dto.OrderEvent;
import com.example.order_Service.event.OrderEventProducer;
import com.example.order_Service.model.Order;
import com.example.order_Service.model.OrderStatus;
import com.example.order_Service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
        
		@Autowired
        private final OrderRepository orderRepository;
		@Autowired
        private final OrderEventProducer orderEventProducer;
        
        
      
	    @Override
	    public Order placeOrder(Order order) {
	         order.setStatus(OrderStatus.NEW);
	         Order od=orderRepository.save(order);
	         OrderEvent event = new OrderEvent();
	         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		     String username = auth.getName();
		     System.out.println(username);
	         event.setCustomerEmail(username);
	         event.setOrderId(od.getId());
	         event.setStatus("CREATED");
	         event.setOrder(od);
	         orderEventProducer.sendOrderEvent(event);
	        return od;
	    }

	    @Override
	    public Order getOrder(Long id) {
	        return orderRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
	    }

	    @Override
	    public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	    }

	    @Override
	    public void deleteOrder(Long id) {
	        orderRepository.deleteById(id);
	    }
}


