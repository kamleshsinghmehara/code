package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Product;

/**
 * CRUD Repository for Product entity
 * @author Kamlesh
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}