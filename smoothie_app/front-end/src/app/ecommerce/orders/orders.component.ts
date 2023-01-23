import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EcommerceService } from '../services/ecommerce.service';
import { AlertService } from '../_alert';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  orders: any[] = [];

  constructor(private ecommerceService: EcommerceService,
    private alertService: AlertService,
    private router: Router) { }

  ngOnInit() {
    this.loadOrders();
  }

  // load all orders
  loadOrders() {
    this.ecommerceService.getAllOrders().subscribe(
      (orders: any) => {
        this.orders = orders;
      },
      (error) => {
        this.alertService.error('Error while loading orders', error);
      });
  }

  // go back to home 
  back() {
    this.router.navigateByUrl('/home');
  }

}
