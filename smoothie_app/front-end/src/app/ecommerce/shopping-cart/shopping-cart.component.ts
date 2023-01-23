import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {ProductOrders} from "../models/product-orders.model";
import {ProductOrder} from "../models/product-order.model";
import {EcommerceService} from "../services/ecommerce.service";
import {Subscription} from "rxjs/internal/Subscription";
import { Router } from '@angular/router';

//Component for shopping cart i.e. smoothie basket
@Component({
    selector: 'app-shopping-cart',
    templateUrl: './shopping-cart.component.html',
    styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit, OnDestroy {
    orderFinished: boolean;
    orders: ProductOrders;
    total: number;
    sub: Subscription;

    @Output() onOrderFinished: EventEmitter<boolean>;

    constructor(private ecommerceService: EcommerceService, private router: Router) {
        this.total = 0;
        this.orderFinished = false;
        this.onOrderFinished = new EventEmitter<boolean>();
    }

    ngOnInit() {
        this.orders = new ProductOrders();
        this.loadCart();
        this.loadTotal();
    }

    // method to compute total payable amount
    private calculateTotal(products: ProductOrder[]): number {
        let sum = 0;
        products.forEach(value => {
            sum += (value.product.price * value.quantity);
        });
        return sum;
    }
    
    // method to load total amount payable
    loadTotal() {
        this.sub = this.ecommerceService.OrdersChanged.subscribe(() => {
            this.total = this.calculateTotal(this.orders.productOrders);
        });
    }
    
    //load order and toal in smoothie basket i.e. cart
    loadCart() {
        this.sub = this.ecommerceService.ProductOrderChanged.subscribe(() => {
            let productOrder = this.ecommerceService.SelectedProductOrder;
            if (productOrder) {
                this.orders.productOrders.push(new ProductOrder(
                    productOrder.product, productOrder.quantity));
            }
            this.ecommerceService.ProductOrders = this.orders;
            this.orders = this.ecommerceService.ProductOrders;
            this.total = this.calculateTotal(this.orders.productOrders);
        });
    }

    // method to go ahead for payment
    finishOrder() {
        this.ecommerceService.Total = this.total;
        this.router.navigateByUrl('/pay', { state: {data: this.total}});
    }

    // reset flags and variables once order is finished
    reset() {
        this.orderFinished = false;
        this.orders = new ProductOrders();
        this.orders.productOrders = []
        this.loadTotal();
        this.total = 0;
    }

    // unsubscribe to subjects upon component destroy
    ngOnDestroy() {
        this.sub.unsubscribe();
    }

}