import {ProductOrder} from "../models/product-order.model";
import {Subject} from "rxjs/internal/Subject";
import {ProductOrders} from "../models/product-orders.model";
import {HttpClient} from '@angular/common/http';
import {Injectable} from "@angular/core";
import { Product } from "../models/product.model";

// Service for product(smoothie) and order CRUD operations
@Injectable()
export class EcommerceService {
    private productsUrl = "/api/products";
    private ordersUrl = "/api/orders";

    private productOrder: ProductOrder;
    private orders: ProductOrders = new ProductOrders();

    private total: number;

    private productOrderSubject = new Subject();
    private ordersSubject = new Subject();
    private totalSubject = new Subject();

    ProductOrderChanged = this.productOrderSubject.asObservable();
    OrdersChanged = this.ordersSubject.asObservable();
    TotalChanged = this.totalSubject.asObservable();

    constructor(private http: HttpClient) {}

    // get all products i.e. smoothies
    getAllProducts() {
        return this.http.get(this.productsUrl);
    }

    // add a product(smoothie)
    addProduct(product: Product) {
        return this.http.post(this.productsUrl, product);
    }

    // update product(smoothie)
    updateProduct(product: Product) {
        return this.http.put(this.productsUrl+'/'+ product.id, product);
    }

    // delete product(smoothie) by id
    deleteProduct(productId: number) {
        return this.http.delete(this.productsUrl+'/'+ productId);
    }

    // get all orders
    getAllOrders() {
        return this.http.get(this.ordersUrl);
    }

    // save order 
    saveOrder(order: ProductOrders) {
        return this.http.post(this.ordersUrl, order);
    }

    // convenience method for setting selected product order in product order subject 
    set SelectedProductOrder(value: ProductOrder) {
        this.productOrder = value;
        this.productOrderSubject.next(value);
    }

    // convenience method for getting selected product order
    get SelectedProductOrder() {
        return this.productOrder;
    }

    // convenience method for setting all selected product order in order subject
    set ProductOrders(value: ProductOrders) {
        this.orders = value;
        this.ordersSubject.next(value);
    }

    // convenience method for getting all selected product order
    get ProductOrders() {
        return this.orders;
    }

    // convenience method for getting total amount
    get Total() {
        return this.total;
    }

    // convenience method for setting total amount in total subject
    set Total(value: number) {
        this.total = value;
        this.totalSubject.next(value);
    }
}