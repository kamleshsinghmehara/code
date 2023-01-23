package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.OrderProduct;
import com.app.repository.OrderProductRepository;

/**
 * Service for OrderProduct entity
 * @author Kamlesh
 *
 */
@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
    private OrderProductRepository orderProductRepository;

    /**
     *Method to create OrderProduct entity
     */
    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}