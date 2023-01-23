package com.app.service;

import org.springframework.validation.annotation.Validated;

import com.app.model.Order;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Service Interface for Order entity
 * @author Kamlesh
 *
 */
@Validated
public interface OrderService {

    /**
     * Method to get all orders
     * @return List of Order
     */
    @NotNull Iterable<Order> getAllOrders();

    /**
     * Method to create order
     * @param order
     * @return Order
     */
    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    /**
     * Method to update order
     * @param order
     */
    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
}