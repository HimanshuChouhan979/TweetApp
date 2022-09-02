import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { Observable } from "rxjs";
import { LoginService } from "./loginservice/login.service";

let headers = new HttpHeaders()
   .set('content-type','application/json')
   .set('Access-Control-Allow-Origin', '*')

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

  
constructor(private loginService:LoginService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const yourToken = this.loginService.getToken();

   if (yourToken) {
      req = req.clone({
         setHeaders: {            
            Authorization: `Bearer ${yourToken}`
         }
      });
   }
   // } else {
   //    req = req.clone({
   //       setHeaders: {
   //          'Content-Type': 'application/json; charset=utf-8',
   //          Accept: 'application/json'
   //       }
   //    });
   // }

   return next.handle(req);

    }

}