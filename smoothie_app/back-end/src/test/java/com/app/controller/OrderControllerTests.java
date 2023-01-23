package com.app.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.app.model.Order;
import com.app.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@ActiveProfiles(value = "test")
public class OrderControllerTests {
  @MockBean
  private OrderRepository orderRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldReturnListOfOrders() throws Exception {
    List<Order> orders = new ArrayList<>();
    orders.add(new Order(3L, LocalDate.parse("2023-01-24"), "PAID", null));
    orders.add(new Order(2L, LocalDate.parse("2023-01-23"), "PAID", null));
    orders.add(new Order(1L, LocalDate.parse("2023-01-21"), "PAID", null));
    
    when(orderRepository.findAll()).thenReturn(orders);
    mockMvc.perform(get("/api/orders"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(orders.size()))
        .andDo(print());
  }
  
  @Test
  void shouldCreateOrder() throws Exception {
	  Order order = new Order(4L, LocalDate.now(), "PAID", null);

    mockMvc.perform(post("/api/orders").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(order)))
        .andExpect(status().isCreated())
        .andDo(print());
  }
  
  
}
