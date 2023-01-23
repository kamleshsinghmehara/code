import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {EcommerceService} from "./ecommerce/services/ecommerce.service";
import { finalize } from 'rxjs/operators';
import { AuthenticationService } from './ecommerce/services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [EcommerceService]
})
export class AppComponent {
  appTitle = 'The Smoothie Shop';
  showLogout:boolean=false;
  loggedInUserName:any;

  constructor(private authenticationService: AuthenticationService, private http: HttpClient, private router: Router) {
    this.showLogout = this.authenticationService.isLoggedIn;
    this.loggedInUserName = this.authenticationService.getCurrentUserName();
    this.authenticationService.logOutChanged.subscribe(
      (data:any) => {
        this.showLogout = data;
      }
    );

    this.authenticationService.loggedInUserChanged.subscribe(
      (data:any) => {
        this.loggedInUserName = data.username;
      }
    );
    
  }

  signOut() {
    let removeToken = localStorage.removeItem('access_token');
    let userRole = localStorage.removeItem('user_role');
    let userName = localStorage.removeItem('user_name');
    if (removeToken == null && userRole == null && userName == null) {
      this.authenticationService.enableLogOutSubject.next(false);
      this.authenticationService.loggedInUserSubject.next({})
      this.router.navigate(['signin']);
    }
  }
}
