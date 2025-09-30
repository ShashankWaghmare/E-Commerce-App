package com.example.order_Service.dto;

import org.springframework.stereotype.Component;

import com.example.order_Service.model.Order;

@Component
public class OrderEvent {
    public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public OrderEvent(Long orderId, String status, String customerEmail,Order order) {
		this.orderId = orderId;
		this.status = status;
		this.customerEmail = customerEmail;
		this.order=order;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderEvent() {
		
	}
	private Long orderId;
    private String status;
    private String customerEmail;
    private Order order;

}
