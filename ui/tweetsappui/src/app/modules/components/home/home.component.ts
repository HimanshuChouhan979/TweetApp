import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TweetDetails } from 'src/app/common/interface/tweet-details';
import { TweetService } from 'src/app/services/tweetservice/tweet.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  allTweets :TweetDetails[]; 
  postTweet:FormGroup;
  course: string;
  tweetField = new FormControl('');
  isEmpty:boolean= false;
  tweetObj :any ={}

  userName =localStorage.getItem('userName');
  constructor(private tweetService:TweetService) { }

  ngOnInit(): void {
    
    this.tweetService.getAllTweet().subscribe(async(data : any)=>{
      this.allTweets = data;
      if(this.allTweets.length>0){
        console.info("All tweets are : ",this.allTweets);
      }else{
        this.isEmpty =true;
        console.info("List is empty: ",this.allTweets);  
      }

    },(error)=>{

  })

  }
  createTweetPayload(tweetText: any){

    this.tweetObj['tweetMsg']=tweetText;
    this.tweetObj['userId']=localStorage.getItem('userName');
    this.tweetObj['like']=0;
    this.tweetObj['firstName']= localStorage.getItem('firstName');
    this.tweetObj['lastName']= localStorage.getItem('lastName');
    this.tweetObj['time']='2010-10-10';
    this.tweetObj['commetsList']=0;
    
    return this.tweetObj;
  }
  tweetFun(){
    const tweetText = this.tweetField.value;
      console.info("Tweet : "+tweetText);
      
      const payload = this.createTweetPayload(tweetText);

      this.tweetService.postTweet(this.userName,payload).subscribe((data)=>{
          console.info("Tweet status : ",data);
          this.ngOnInit();
      },(errr)=>{

      })

      this.tweetField.setValue('');
  }


  
  addItem(event: any){
    this.ngOnInit();
  }
}
