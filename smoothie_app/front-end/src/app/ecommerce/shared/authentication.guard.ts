import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../_alert';

// Authentication Guard service for protecting routes 
@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  constructor(public authenticationService: AuthenticationService, public alertService: AlertService, public router: Router) { }

  // only authenticated users can go to protected routes such as add/edit/home
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if (this.authenticationService.isLoggedIn !== true) {
        this.alertService.error("Oh no!!! Access not allowed.", { keepAfterRouteChange: true });
        this.router.navigate(['/signin'])
      }
    return true;
  }
  
}
