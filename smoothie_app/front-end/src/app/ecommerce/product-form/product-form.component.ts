import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { EcommerceService } from '../services/ecommerce.service';
import { Product } from '../models/product.model';
import { AlertService } from '../_alert';

// Component for product add and edit
@Component({
    selector: 'app-product-form',
    templateUrl: './product-form.component.html',
    styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

    @Input() product: Product;
    productForm: FormGroup;
    
    loading = false;
    submitted = false;
    routerState: any;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private ecommerceService: EcommerceService,
        private alertService: AlertService) {
        this.routerState = this.router.getCurrentNavigation()?.extras.state
    }

    ngOnInit() {
        this.productForm = this.formBuilder.group({
            id: [''],
            name: ['', Validators.required],
            price: ['', Validators.required],
            description: [''],
            nutritionValue: this.formBuilder.group({
                id: [''],
                carbohydrate: [''],
                protein: [''],
                fat: [''],
                cholestrol: [''],
                sodium: [''],
                potassium: [''],
                calcium: [''],
                iron: [''],
                calories: ['']
            })
        });

        if (this.routerState) {
            this.productForm.setValue(this.routerState.data);
        }
    }

    // convenience getter for easy access to form fields
    get f() { return this.productForm.controls; }

    // form submit method
    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.productForm.invalid) {
            return;
        }

        this.loading = true;
        if (this.routerState.mode === 'add') {
            this.addProduct(this.productForm.value);
        } else if (this.routerState.mode === 'edit') {
            this.updateProduct(this.productForm.value);
        }
    }

    // method for adding product
    private addProduct(product: Product) {
        this.ecommerceService.addProduct(product)
            .pipe(first())
            .subscribe({
                next: () => {
                    this.alertService.success('Voila!!! a new Smoothie is added.', { keepAfterRouteChange: true });
                    console.log('product added........');
                    this.router.navigate(['/home'], { relativeTo: this.route });
                },
                error: error => {
                    this.loading = false;
                    this.alertService.error(error);
                }
            });
    }

    // method for updating product
    private updateProduct(product: Product) {
        this.ecommerceService.updateProduct(product)
            .pipe(first())
            .subscribe({
                next: () => {
                    this.alertService.success('Voila!!! Smoothie is updated.', { keepAfterRouteChange: true });
                    this.router.navigate(['/home'], { relativeTo: this.route });
                },
                error: error => {
                    this.alertService.error(error);
                    this.loading = false;
                }
            });
    }

    reset() { }

    // go back to home if cancel clicked
    cancel() {
        this.router.navigateByUrl('/home');
    }
}