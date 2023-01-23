package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Product;
import com.app.repository.ProductRepository;

/**
 * Service for Product entity
 * @author Kamlesh
 *
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Method to get all products
	 */
	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	/**
	 * Method to get a product
	 */
	@Override
	public Product getProduct(long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	/**
	 * Method to created/update a product
	 */
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	/**
	 * Method to delete a product by id
	 */
	@Override
	public void delete(long id) {
		productRepository.deleteById(id);
	}
}