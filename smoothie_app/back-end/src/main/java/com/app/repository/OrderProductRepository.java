package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.model.OrderProduct;
import com.app.model.OrderProductPK;

/**
 * CRUD Repository for OrderProduct entity
 * @author Kamlesh
 *
 */
public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}