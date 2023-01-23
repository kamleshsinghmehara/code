package com.app.controller;

import com.app.model.Product;
import com.app.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

import javax.validation.constraints.NotNull;

/**
 * REST Controller class for handling request related to products such as
 * create, update, get and delete products
 * 
 * @author Kamlesh
 *
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Method to get all existing products
	 * 
	 * @return List of products(Smoothies)
	 */
	@GetMapping(value = { "", "/" })
	public @NotNull Iterable<Product> getProducts() {
		return productService.getAllProducts();
	}

	/**
	 * Method to fetch a product(smoothie) by id useful for viewing a single product
	 * 
	 * @param id
	 * @return Product(Smoothie)
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id) {
		try {
			Product product = productService.getProduct(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Method to create a product(smoothie) typically from add product(smoothie)
	 * form
	 * 
	 * @param product
	 */
	@PostMapping
	public void add(@RequestBody Product product) {
		productService.save(product);
	}

	/**
	 * Method to update the product(smoothie) details typically from edit form
	 * 
	 * @param product
	 * @param id
	 * @return status (OK or NOT_FOUND)
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) {
		try {
			Product existProduct = productService.getProduct(id);
			if (existProduct != null) {
				productService.save(product);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Method to delete a product(smoothie) from database in case the
	 * product(smoothie) is no longer required
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		productService.delete(id);
	}
}