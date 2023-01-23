import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

// Service for authentication purpose such as generating token and fetching user details
@Injectable()
export class AuthenticationService {

  public enableLogOutSubject = new Subject();
  logOutChanged = this.enableLogOutSubject.asObservable();

  public loggedInUserSubject = new Subject();
  loggedInUserChanged = this.loggedInUserSubject.asObservable();

  constructor(private http: HttpClient, public router: Router) { }

  // method to get jwt token from server
  generateToken(request: any) {
    return this.http.post<string>("/api/authenticate", request, { responseType: 'text' as 'json' });
  }

  // convenience method to check if user logged In 
  get isLoggedIn(): boolean {
    let authToken = localStorage.getItem('access_token');
    return authToken !== null ? true : false;
  }

  // get jwt token form storage
  getToken() {
    return localStorage.getItem('access_token');
  }

  // get user role from storage
  getRole() {
    return localStorage.getItem('user_role');
  }

  // get logged in user name from storage
  getCurrentUserName() {
    return localStorage.getItem('user_name');
  }

}
