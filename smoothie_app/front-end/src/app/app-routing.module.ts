import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EcommerceComponent } from './ecommerce/ecommerce.component';
import { OrdersComponent } from './ecommerce/orders/orders.component';
import { PaymentComponent } from './ecommerce/payment/payment.component';
import { ProductFormComponent } from './ecommerce/product-form/product-form.component';
import { AuthenticationGuard } from './ecommerce/shared/authentication.guard';
import { SigninFormComponent } from './ecommerce/signin-form/signin-form.component';
import { SignupFormComponent } from './ecommerce/signup-form/signup-form.component';

const routes: Routes = [
  { path: '', redirectTo: '/signin', pathMatch: 'full' },
  { path: 'signin', component: SigninFormComponent },
  { path: 'signup', component: SignupFormComponent },
  { path: 'home', component: EcommerceComponent, canActivate: [AuthenticationGuard] },
  { path: 'edit', component: ProductFormComponent, canActivate: [AuthenticationGuard] },
  { path: 'add', component: ProductFormComponent, canActivate: [AuthenticationGuard] },
  { path: 'pay', component: PaymentComponent, canActivate: [AuthenticationGuard] },
  { path: 'orders', component: OrdersComponent, canActivate: [AuthenticationGuard] }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
