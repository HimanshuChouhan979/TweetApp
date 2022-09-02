import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router} from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment.prod';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  
  url = environment.commonUrl;
  
  constructor(private http:HttpClient,private router:Router) { }

  //calling server to get token
generateToken(credentials:any):Observable<any>{  
 return this.http.post(this.url+"/auth/login",credentials)
}

  setToken(token: string){
   // console.log("loginUser():"+token);
    localStorage.setItem("authtoken",token)   
    return true;
  }

  isLoggedIn(){
    let token=localStorage.getItem("authtoken");
    if(token==undefined || token==='' ||token==null){
      return false;
    }else{
      return true;
    }
  }

  logout(){
    localStorage.removeItem('authtoken');
    this.router.navigate(['login']);
    return true;
  }

  getToken(){
    //console.log(localStorage.getItem('authtoken'));
    return localStorage.getItem('authtoken');
  }

  resetPassword(email:any){
    return this.http.post(this.url+"/forgot",email)
  }

  verifyOtp(otpToken:any){
    
    return this.http.post(this.url+"/verify",otpToken)
  }

  changePassoword(changedCreds:any){
    return this.http.post(this.url+"/change",changedCreds)
  }

}
