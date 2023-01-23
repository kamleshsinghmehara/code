import { Product } from "./product.model";

// for storing product order quantity
export class ProductOrder {
    product: Product;
    quantity: number;
    
    constructor(product: Product, quantity: number) {
        this.product = product;
        this.quantity = quantity;
    }
}