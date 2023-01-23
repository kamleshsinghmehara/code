import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs';
import { AuthenticationService } from '../services/authentication.service';
import { AlertService } from '../_alert';

// Component for log In purpose
@Component({
  selector: 'app-signin-form',
  templateUrl: './signin-form.component.html',
  styleUrls: ['./signin-form.component.css']
})
export class SigninFormComponent implements OnInit {
  
  signinForm: FormGroup;

  constructor(
    public fb: FormBuilder,
    public authenticationService: AuthenticationService,
    public router: Router,
    public alertService: AlertService
  ) {
    this.signinForm = this.fb.group({
      userName: [''],
      password: [''],
    });
  }
  ngOnInit() { }

  // Sign-in method to get jwt token and put in storage
  // any error while sign in displyed using alertservice
  signIn() {
    return this.authenticationService.generateToken(this.signinForm.value)
      .pipe(first())
      .subscribe({
        next: (res: any) => {
          localStorage.setItem('access_token', res);
          let decodedJWT = JSON.parse(window.atob(res.split('.')[1]));

          localStorage.setItem('user_role', decodedJWT.role);
          localStorage.setItem('user_name', decodedJWT.sub);

          this.authenticationService.enableLogOutSubject.next(true);
          this.authenticationService.loggedInUserSubject.next({ 'username': decodedJWT.sub, 'userrole': decodedJWT.role, 'access_token': res });
          this.router.navigate(['/home']);
        },
        error: error => {
          let errorMessage = JSON.parse(error['error'])['message'];
          this.alertService.error(errorMessage);
        }
      });
  }
}