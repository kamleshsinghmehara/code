import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductsComponent } from "./products/products.component";
import { ShoppingCartComponent } from "./shopping-cart/shopping-cart.component";
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';

// Component for holding product(smoothie) list and shopping-cart(smoothie basket)
@Component({
    selector: 'app-ecommerce',
    templateUrl: './ecommerce.component.html',
    styleUrls: ['./ecommerce.component.css']
})
export class EcommerceComponent implements OnInit {

    userRole: any;

    @ViewChild('productsC')
    productsC: ProductsComponent;

    @ViewChild('shoppingCartC')
    shoppingCartC: ShoppingCartComponent;

    constructor(private authenticationService: AuthenticationService, private http: HttpClient, private router: Router) {
        this.userRole = this.authenticationService.getRole();
    }

    ngOnInit() { }

    // rest product and shopping cart component data
    reset() {
        this.productsC.reset();
        this.shoppingCartC.reset();
    }

    // add a new product(smoothie)
    addProduct() {
        this.router.navigateByUrl('/add', { state: { data: {}, mode: 'add' } });
    }

    // add a new product(smoothie)
    viewAllOrders() {
        this.router.navigateByUrl('/orders', { state: { data: {}, mode: 'add' } });
    }

}