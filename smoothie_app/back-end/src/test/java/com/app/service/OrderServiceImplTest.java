package com.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.model.Order;
import com.app.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceImplTest {
	
	@Autowired
	private OrderService orderService;

	@MockBean
	private OrderRepository orderRepository;

	@Test
	void testGetAllOrders() {
		
		List<Order> orders = new ArrayList<>();
	    orders.add(new Order(3L, LocalDate.parse("2023-01-24"), "PAID", null));
	    orders.add(new Order(2L, LocalDate.parse("2023-01-23"), "PAID", null));
	    orders.add(new Order(1L, LocalDate.parse("2023-01-21"), "PAID", null));
	    
	    when(orderRepository.findAll()).thenReturn(orders);
		List<Order> target = new ArrayList<>();
		orderService.getAllOrders().iterator().forEachRemaining(target::add);
		assertThat(target.size()).isEqualTo(orders.size());
	}

	@Test
	void testCreate() {
		Order order = new Order(4L, LocalDate.now(), "PAID", null);
		Order createdOrder = orderService.create(order);
		assertThat(createdOrder.getId()).isEqualTo(order.getId());
		assertThat(createdOrder.getDateCreated()).isEqualTo(order.getDateCreated());
		assertThat(createdOrder.getNumberOfProducts()).isEqualTo(order.getNumberOfProducts());
		assertThat(createdOrder.getStatus()).isEqualTo(order.getStatus());
		assertThat(createdOrder.getTotalOrderPrice()).isEqualTo(order.getTotalOrderPrice());
	}

}
