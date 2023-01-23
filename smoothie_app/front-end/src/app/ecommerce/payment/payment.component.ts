import { Component, OnInit } from '@angular/core';
import { ProductOrders } from "../models/product-orders.model";
import { Subscription } from "rxjs/internal/Subscription";
import { EcommerceService } from "../services/ecommerce.service";
import { Router } from '@angular/router';

// Component for payment
@Component({
    selector: 'app-payment',
    templateUrl: './payment.component.html',
    styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

    orders: ProductOrders;
    total: number;
    paid: boolean;
    routerState: any;

    constructor(private ecommerceService: EcommerceService, private router: Router) {
        this.orders = this.ecommerceService.ProductOrders;
        this.routerState = this.router.getCurrentNavigation()?.extras.state
    }

    ngOnInit() {
        this.paid = false;
        //get total from router state
        if (this.routerState) {
            this.total = this.routerState.data;
        }
    }

    // proceed for making payments
    pay() {
        this.paid = true;
        this.ecommerceService.saveOrder(this.orders).subscribe();
    }

    // go back to home 
    back() {
        this.router.navigateByUrl('/home');
    }

}
