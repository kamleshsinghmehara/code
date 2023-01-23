package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Order;

/**
 * CRUD Repository for Order entity
 * @author Kamlesh
 *
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
	
}
