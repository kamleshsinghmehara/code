import { Component, OnInit, Output } from '@angular/core';
import { ProductOrder } from "../models/product-order.model";
import { EcommerceService } from "../services/ecommerce.service";
import { ProductOrders } from "../models/product-orders.model";
import { Product } from "../models/product.model";
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../_alert';

// Component for all products(smoothies) view
@Component({
    selector: 'app-products',
    templateUrl: './products.component.html',
    styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

    productOrders: ProductOrder[] = [];
    products: Product[] = [];
    selectedProductOrder: ProductOrder;
    shoppingCartOrders: ProductOrders;
    productSelected: boolean = false;

    closeResult: string = '';

    userRole: any;

    constructor(
        private authenticationService: AuthenticationService,
        private ecommerceService: EcommerceService,
        private alertService: AlertService,
        private router: Router) {
        this.userRole = this.authenticationService.getRole();
    }

    ngOnInit() {
        this.productOrders = [];
        this.loadProducts();
        this.loadOrders();
    }

    // add product order to cart
    addToCart(order: ProductOrder) {
        this.ecommerceService.SelectedProductOrder = order;
        this.selectedProductOrder = this.ecommerceService.SelectedProductOrder;
        this.productSelected = true;
    }

    // remove product order from cart
    removeFromCart(productOrder: ProductOrder) {
        let index = this.getProductIndex(productOrder.product);
        if (index > -1) {
            this.shoppingCartOrders.productOrders.splice(
                this.getProductIndex(productOrder.product), 1);
        }
        this.ecommerceService.ProductOrders = this.shoppingCartOrders;
        this.shoppingCartOrders = this.ecommerceService.ProductOrders;
        this.productSelected = false;
    }

    // get product index
    getProductIndex(product: Product): number {
        return this.ecommerceService.ProductOrders.productOrders.findIndex(
            value => value.product === product);
    }

    // to check if product selected
    isProductSelected(product: Product): boolean {
        return this.getProductIndex(product) > -1;
    }

    // load all products(smoothies)
    loadProducts() {
        this.ecommerceService.getAllProducts().subscribe(
            (products: any) => {
                this.products = products;
                this.products.forEach(product => {
                    this.productOrders.push(new ProductOrder(product, 0));
                })
            },
            (error) => {
                this.alertService.error('Error while loading smoothies', error);
            });
    }

    // load all orders
    loadOrders() {
        this.ecommerceService.OrdersChanged.subscribe(() => {
            this.shoppingCartOrders = this.ecommerceService.ProductOrders;
        },
            (error) => {
                this.alertService.error('Error while loading orders', error);
            });
    }

    // delete a product
    deleteProduct(productId: number) {
        this.ecommerceService.deleteProduct(productId).subscribe(() => {
            this.productOrders = this.productOrders.filter((order: any) => order.product.id !== productId);
        },
            (error) => {
                this.alertService.error('Error while deleting smoothie', error);
            });
    }

    // edit a product
    editProduct(product: Product) {
        this.router.navigateByUrl('/edit', { state: { data: product, mode: 'edit' } });
    }


    // reset all data
    reset() {
        this.productOrders = [];
        this.loadProducts();
        this.ecommerceService.ProductOrders.productOrders = [];
        this.loadOrders();
        this.productSelected = false;
    }
}