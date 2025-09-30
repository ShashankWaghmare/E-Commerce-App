package com.example.order_Service.serviceLayer;

import org.mockito.junit.jupiter.MockitoExtension;

import com.example.order_Service.dto.OrderEvent;
import com.example.order_Service.event.OrderEventProducer;
import com.example.order_Service.model.Order;
import com.example.order_Service.model.OrderStatus;
import com.example.order_Service.repository.OrderRepository;
import com.example.order_Service.service.OrderServiceImpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.security.core.Authentication;

import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
public class ServiceLayerTest {
     
	

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	
	@Mock
	private OrderRepository orderRepository;
    
	
    @Mock
    private OrderEventProducer orderEventProducer; 
	
	@Test
	public void TestPlaceOrder() {
		Order od=new Order();
		od.setOrderNumber("123456");
		od.setProductCode("ABCDE");
		od.setQuantity(5);
		od.setStatus(OrderStatus.NEW);
		
	    Authentication authentication = Mockito.mock(Authentication.class);
	    Mockito.when(authentication.getName()).thenReturn("testUser");

	    SecurityContext securityContext = Mockito.mock(SecurityContext.class);
	    Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

	    SecurityContextHolder.setContext(securityContext);
		Mockito.when(orderRepository.save(od)).thenReturn(od);
		
        Mockito.doNothing().when(orderEventProducer).sendOrderEvent(Mockito.any(OrderEvent.class));
        
		
		Order od1=orderServiceImpl.placeOrder(od);
		assertEquals(od1,od);
		Mockito.verify(orderEventProducer).sendOrderEvent(any(OrderEvent.class));
		SecurityContextHolder.clearContext();
	}
}
