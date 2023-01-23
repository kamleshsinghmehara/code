package com.app.service;

import org.springframework.validation.annotation.Validated;

import com.app.model.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Service Interface for Product entity
 * @author Kamlesh
 *
 */
@Validated
public interface ProductService {

    /**
     * Method to get all products
     * @return List of Products
     */
    @NotNull Iterable<Product> getAllProducts();

    /**
     * Method to get a product
     * @param id
     * @return Product
     */
    Product getProduct(@Min(value = 1L, message = "Invalid product ID.") long id);

    /**
     * Method to create/update a product
     * @param product
     * @return Product
     */
    Product save(Product product);
    
    /**
     * Method to delete a product by id
     * @param id
     */
    void delete(long id);
}