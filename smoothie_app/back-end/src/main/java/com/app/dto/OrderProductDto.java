package com.app.dto;

import com.app.model.Product;

import lombok.Data;

/**
 * Class for Order details having product and quantity
 * @author Kamlesh
 *
 */
@Data
public class OrderProductDto {

    private Product product;
    private Integer quantity;
    
}