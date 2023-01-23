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

import com.app.model.AuthRequest;
import com.app.model.Order;
import com.app.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@ActiveProfiles(value = "test")
public class JwtAuthControllerTests {
  @MockBean
  private OrderRepository orderRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  
  @Test
  void shouldGenerateToken() throws Exception {
	  AuthRequest authRequest = new AuthRequest();
	  authRequest.setUserName("Alex");
	  authRequest.setPassword("owner");

    mockMvc.perform(post("/api/authenticate").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(authRequest)))
        .andExpect(status().isOk())
        .andDo(print());
  }
  
  
}
