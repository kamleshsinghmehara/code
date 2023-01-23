import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler } from "@angular/common/http";
import { AuthenticationService } from "../services/authentication.service";

// Interceptor service to intercept every request and put jwt token(if available) in request header
@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
    
    constructor(private authenticationService: AuthenticationService) { }
    
    // method to handle each request and set authorization header
    intercept(req: HttpRequest<any>, next: HttpHandler) {
        const authToken = this.authenticationService.getToken();
        if (authToken) {
            req = req.clone({
                setHeaders: {
                    Authorization: "Bearer " + authToken
                }
            });
        }
        return next.handle(req);
    }
}