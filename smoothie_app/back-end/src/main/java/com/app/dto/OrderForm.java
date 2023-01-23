package com.app.dto;

import java.util.List;

import lombok.Data;

/**
 * Class depicting Order Form value details
 * @author Kamlesh
 *
 */
@Data
public class OrderForm {

    private List<OrderProductDto> productOrders;

}