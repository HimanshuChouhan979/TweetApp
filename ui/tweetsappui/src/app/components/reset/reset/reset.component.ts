import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/loginservice/login.service';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css']
})
export class ResetComponent implements OnInit {

  alertMsg:any
  email:any
  successMsg:any
  otp:any
  resetStatus:any
  sendOtpStatus:any
  password1:any
  password2:any
  verifyStatus:any
  diasbledButton:boolean

  otpToken={
    uid:'',
    authToken:''    
  }

  changedCreds={
    uid:'',
    password:''
  }
  

  constructor(private loginService:LoginService) {
    this.resetStatus=false;
    this.sendOtpStatus=false;
    this.verifyStatus=false;
    this.diasbledButton=false;
   }

  ngOnInit(): void {
    
    
  }
  checkValue(){
    if (this.password1!='' && this.password1!=null && this.password2!='' && this.password2!=null && (this.password1==this.password2)) {
      return true;
    } else {
      return false;
    }
  }

  

  onSubmit(){
    if((this.email!='' )&& (this.email!=null)){
      console.log("submit for sending otp");
      this.loginService.resetPassword(this.email).subscribe(
        (response:any)=>{
          console.log(response);
          this.successMsg="OTP sent on email !";
          this.sendOtpStatus=true;
          this.alertMsg=null;

                    
        },
        (error:any)=>{
         
         this.alertMsg= error.error.Message;
          console.log(error);
          this.successMsg=null;
        }      
          
      )
    }else{
      console.log('Fields are empty!!');
    }
    this.alertMsg=null;
    this.successMsg=null;
  }
  onSubmitOtp(){
    if((this.email!='' && this.otp!='' )&& (this.email!=null && this.otp!=null)){
    console.log("submit for verify");
    this.otpToken.uid=this.email;
        this.otpToken.authToken=this.otp;
    this.loginService.verifyOtp(this.otpToken).subscribe(
      (response:any)=>{
        console.log(response);
        this.successMsg="OTP verified successfully !";        
        this.verifyStatus=true;
        // this.sendOtpStatus=false;          
        this.alertMsg=null;   
        
      },
      (error:any)=>{
        console.log(error);
       this.alertMsg= error.error.Message;       
        this.successMsg=null;            
      }
        
    )
  }else{
    console.log('Fields are empty!!');
  }
  this.alertMsg=null;
  this.successMsg=null;
  }
  onChangePassword(){if((this.email!='' && this.password1!='' && this.password2!='' )&& (this.email!=null && this.password1!=null && this.password2!=null)
  && this.password1==this.password2){

    console.log("submit for changing password");
    this.changedCreds.uid=this.email;
        this.changedCreds.password=this.password1;
    this.loginService.changePassoword(this.changedCreds).subscribe(
      (response:any)=>{
        console.log(response);
        
        this.resetStatus=true;    
        this.successMsg="Password Changed Successfully !";    
        this.alertMsg=null;   
        window.location.href="/login"
                 
        
      },
      (error:any)=>{
       
       this.alertMsg= error.error.Message;
       this.successMsg=null;            
        console.log(error);
      }
        
    )
  }else{
    console.log('Fields are empty!!');
  }
  this.alertMsg=null;
  this.successMsg=null;
  }

}
