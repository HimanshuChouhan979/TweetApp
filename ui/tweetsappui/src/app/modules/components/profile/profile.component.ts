import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TweetDetails } from 'src/app/common/interface/tweet-details';
import { TweetService } from 'src/app/services/tweetservice/tweet.service';
import { UserService } from 'src/app/services/userservice/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private userService:UserService,private tweetService:TweetService,private router:Router) { }

  userDetails:any ={};
  joinedDate :any;
  tweetByUser : TweetDetails[];
  isEmpty:boolean= false;



  ngOnInit(): void {
    this.userService.getUser(localStorage.getItem('userName')).subscribe((data:any)=>{
     
      this.userDetails.userId = data.userName; 
      this.userDetails.firstName=data.firstName;
      this.userDetails.lastName=data.lastName;
      this.userDetails.name = data.lastName+","+data.firstName; 
      this.userDetails.lastName = data.lastName; 
      this.userDetails.gender = data.gender; 
      this.userDetails.email = data.email;
      this.userDetails.dateOfBirth = data.dateOfBirth; 
     
    }, (error: HttpErrorResponse) => {
      console.log('Error message ' + error.message);
      if (error instanceof Error) {
      } else {
      }
    });

    
    this.tweetService.getAllTweetByUserId(localStorage.getItem('userName')).subscribe(async(data)=>{
      this.tweetByUser = data;
      if(this.tweetByUser.length>0){
        console.info("All tweets are : ",this.tweetByUser);
      }else{
        this.isEmpty =true;
        console.info("List is empty: ",this.tweetByUser);  
      }
    },(error)=>{});

 
  }

  back(){
    this.router.navigate(['user/home'])
  }
  addItem(event: any){
    this.ngOnInit();
  }

}
