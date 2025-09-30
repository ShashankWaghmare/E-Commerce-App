package com.example.order_Service.service;

import java.util.List;

import com.example.order_Service.model.Order;

public interface OrderService {
	Order placeOrder(Order order);
    Order getOrder(Long id);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
}
