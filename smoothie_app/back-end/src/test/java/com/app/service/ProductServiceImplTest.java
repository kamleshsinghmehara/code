package com.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.model.Product;
import com.app.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@Test
	void testGetAllProducts() {
		List<Product> products = new ArrayList<>();
		products.add(new Product(1L, "A", 2.35, "Description A", null));
		products.add(new Product(2L, "B", 3.50, "Description B", null));
		products.add(new Product(3L, "c", 5.15, "Description C", null));

		when(productRepository.findAll()).thenReturn(products);
		
		List<Product> target = new ArrayList<>();
		productService.getAllProducts().iterator().forEachRemaining(target::add);
		
		assertThat(target.size()).isEqualTo(products.size());
	}

	@Test
	void testGetProduct() {
		Product product = new Product(1L, "A", 2.35, "Description A", null);
		assertThat(productService.getProduct(product.getId()).getName()).isEqualTo(product.getName());
		assertThat(productService.getProduct(product.getId()).getDescription()).isEqualTo(product.getDescription());
		assertThat(productService.getProduct(product.getId()).getPrice()).isEqualTo(product.getPrice());
	}

	@Test
	void testSave() {
		Product product = new Product(1L, "A", 2.35, "Description A", null);
		Product savedProduct = productService.save(product);
		assertThat(savedProduct.getName()).isEqualTo(product.getName());
		assertThat(savedProduct.getDescription()).isEqualTo(product.getDescription());
		assertThat(savedProduct.getPrice()).isEqualTo(product.getPrice());
	}

}
