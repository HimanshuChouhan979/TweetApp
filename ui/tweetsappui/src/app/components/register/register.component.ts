import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegisterService } from 'src/app/services/registerservice/register.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private registerService:RegisterService,private router: Router) { }

  userDetails={
    userName: '',
    firstName: '',
    lastName: '',
    dateOfBirth: '',
    email: '',
    password: '',
    gender: '',
    contactNumber:''
  }

  alertMsg:any
  successMsg:any

  ngOnInit(): void {}

  
  onSubmit(){
    if((this.userDetails.userName!='' && this.userDetails.firstName!='' && this.userDetails.lastName!='' && this.userDetails.email!='' && this.userDetails.dateOfBirth!='' && this.userDetails.contactNumber!='' && this.userDetails.gender!='' && this.userDetails.password!='') 
    &&(this.userDetails.userName!=null && this.userDetails.firstName!=null && this.userDetails.lastName!=null && this.userDetails.email!=null && this.userDetails.dateOfBirth!=null && this.userDetails.contactNumber!=null && this.userDetails.gender!=null && this.userDetails.password!=null)){
      console.log("submit for register");
      this.registerService.registerUser(this.userDetails).subscribe(
        (data:any)=>{
                   
          this.successMsg= "Registeration Successfull!!";
          
          console.log(data);
          
        },
        (error:any)=>{
          
          this.alertMsg= error.error.Message;
          
        }
          
      )
    }else{
      this.alertMsg="One or more mandatory information is missing"
      console.log('Fields are empty!!');
    }
  }

}
