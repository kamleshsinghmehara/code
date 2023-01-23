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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.app.model.Product;
import com.app.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles(value = "test")
public class ProductControllerTests {
  @MockBean
  private ProductRepository productRepository;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void shouldReturnListOfProducts() throws Exception {
    List<Product> products = new ArrayList<>();
    products.add(new Product(1L, "A", 2.35, "Description A", null));
    products.add(new Product(2L, "B", 3.50, "Description B", null));
    products.add(new Product(3L, "c", 5.15, "Description C", null));
    
    when(productRepository.findAll()).thenReturn(products);
    mockMvc.perform(get("/api/products"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(products.size()))
        .andDo(print());
  }
  
  @Test
  void shouldCreateProduct() throws Exception {
	  Product product = new Product(1L, "A", 2.35, "Description A", null);

    mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(product)))
        .andExpect(status().isCreated())
        .andDo(print());
  }
  
  @Test
  void shouldReturnProduct() throws Exception {
    long id = 1L;
    Product product = new Product(1L, "A", 2.35, "Description A", null);

    when(productRepository.findById(id)).thenReturn(Optional.of(product));
    mockMvc.perform(get("/api/products/{id}", id)).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id))
        .andExpect(jsonPath("$.name").value(product.getName()))
        .andExpect(jsonPath("$.description").value(product.getDescription()))
        .andExpect(jsonPath("$.price").value(product.getPrice()))
        .andDo(print());
  }
  
  @Test
  void shouldReturnNotFoundProduct() throws Exception {
    long id = 4L;

    when(productRepository.findById(id)).thenReturn(Optional.empty());
    mockMvc.perform(get("/api/products/{id}", id))
         .andExpect(status().isNotFound())
         .andDo(print());
  }
  
  @Test
  void shouldUpdateProduct() throws Exception {
    long id = 1L;

    Product product = new Product(1L, "A", 2.35, "Description A", null);
    Product updatedProduct = new Product(1L, "A New", 3.33, "Description A", null);

    when(productRepository.findById(id)).thenReturn(Optional.of(product));
    when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

    mockMvc.perform(put("/api/products/{id}", id).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedProduct)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value(updatedProduct.getName()))
        .andExpect(jsonPath("$.description").value(updatedProduct.getDescription()))
        .andExpect(jsonPath("$.price").value(updatedProduct.getPrice()))
        .andDo(print());
  }
  
  @Test
  void shouldDeleteProduct() throws Exception {
    long id = 1L;

    doNothing().when(productRepository).deleteById(id);
    mockMvc.perform(delete("/api/products/{id}", id))
         .andExpect(status().isNoContent())
         .andDo(print());
  }
}
