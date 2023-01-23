package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Order;
import com.app.repository.OrderRepository;

import java.time.LocalDate;

/**
 * Service for Order entity
 * @author Kamlesh
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
    private OrderRepository orderRepository;

    /**
     * Method to get all orders
     */
    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    /**
     * Method to create an Order
     */
    @Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());

        return this.orderRepository.save(order);
    }

    /**
     * Method to update order
     */
    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }
}