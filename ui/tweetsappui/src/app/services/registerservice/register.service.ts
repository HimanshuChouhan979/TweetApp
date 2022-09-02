import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {


  url = environment.commonUrl;
  
  constructor(private http:HttpClient) { }

  //calling server to get token
registerUser(userDetails:any){
  return this.http.post(this.url+"/register",userDetails)
}
}
