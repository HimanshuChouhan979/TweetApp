import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/loginservice/login.service';
import { UserService } from 'src/app/services/userservice/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  credentials={
    uid:'',
    password:''
  }
  alertMsg:any
  
  constructor(private loginService:LoginService,private router:Router,private userService:UserService) { }

  ngOnInit(): void {
    if(this.loginService.isLoggedIn())
      this.router.navigate(['user']);
  }

  onSubmit(){
    if((this.credentials.uid!='' && this.credentials.password!='')&& (this.credentials.uid!=null && this.credentials.password!=null)){
      console.log("submit for login");
       this.loginService.generateToken(this.credentials)
    .subscribe(
        (response:any)=>{
          console.log(response);
          this.loginService.setToken(response.authToken)
          console.log("islogged in="+this.loginService.isLoggedIn());
          this.userService.setUserName(this.credentials.uid);
     
          this.userService.getUser(this.credentials.uid).subscribe((data)=>{
            this.userService.setFirstName(data.firstName);            
            this.userService.setLastName(data.lastName);            
            console.log("user data="+data);
          });
    
          this.router.navigate(['user']);
        },
        (error:any)=>{         
         this.alertMsg= "Invalid Username/Password";
          console.log(error);
        }          
      )
    }else{
      console.log('Fields are empty!!');
    }     
  
  }

  
  

}
