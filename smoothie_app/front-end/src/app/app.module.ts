import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { EcommerceComponent } from './ecommerce/ecommerce.component';
import { ProductsComponent } from './ecommerce/products/products.component';
import { ShoppingCartComponent } from './ecommerce/shopping-cart/shopping-cart.component';
import { PaymentComponent } from './ecommerce/payment/payment.component';
import { EcommerceService } from "./ecommerce/services/ecommerce.service";
import { ProductFormComponent } from './ecommerce/product-form/product-form.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthenticationInterceptor } from './ecommerce/shared/authentication.interceptor';
import { SigninFormComponent } from './ecommerce/signin-form/signin-form.component';
import { SignupFormComponent } from './ecommerce/signup-form/signup-form.component';
import { AlertModule } from './ecommerce/_alert';
import { AuthenticationService } from './ecommerce/services/authentication.service';
import { OrdersComponent } from './ecommerce/orders/orders.component';

@NgModule({
    declarations: [
        AppComponent,
        EcommerceComponent,
        ProductsComponent,
        ShoppingCartComponent,
        PaymentComponent,
        ProductFormComponent,
        SigninFormComponent,
        SignupFormComponent,
        OrdersComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        NgbModule,
        AppRoutingModule,
        AlertModule
    ],
    providers: [
        EcommerceService,
        AuthenticationService,
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthenticationInterceptor,
            multi: true
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}