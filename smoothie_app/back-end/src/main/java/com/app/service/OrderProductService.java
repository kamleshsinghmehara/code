package com.app.service;

import org.springframework.validation.annotation.Validated;

import com.app.model.OrderProduct;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Service Interface for OrderProduct entity
 * @author Kamlesh
 *
 */
@Validated
public interface OrderProductService {

    /**
     * Method to create OrderProduct
     * @param orderProduct
     * @return OrderProduct
     */
    OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);
}