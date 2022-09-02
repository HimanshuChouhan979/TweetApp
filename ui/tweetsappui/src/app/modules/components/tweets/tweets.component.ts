import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';
import { TweetDetails } from 'src/app/common/interface/tweet-details';
import { TweetService } from 'src/app/services/tweetservice/tweet.service';
import {Comment} from 'src/app/common/interface/comment';
import { UserService } from 'src/app/services/userservice/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tweets',
  templateUrl: './tweets.component.html',
  styleUrls: ['./tweets.component.css']
})
export class TweetsComponent implements OnInit {
  @Input() tweet: TweetDetails;
  @Output() newItemEvent = new EventEmitter();
  
  isLikeed: boolean=false;
  count: number;
  tweetField = new FormControl('');
  editTweet= new FormControl('');
  userName= localStorage.getItem('userName');
  isShowDiv = true;
  user= localStorage.getItem('user');
  constructor(private tweetService: TweetService,private userService:UserService, private router:Router) {}
  ngOnChange() {}

  toggleDisplayDiv() {  
    this.isShowDiv = !this.isShowDiv;  
  }  
 
  ngOnInit(): void {
    console.info('is like value', this.isLikeed);
    this.count = this.tweet.like;
    this.editTweet.setValue(this.tweet.tweetMsg);
    console.log("tweet id="+this.tweet.id);
    this.userService
      .getUser(this.userName)
      .subscribe(
        (data) => {
          console.info("Tweets liked : ",data.likedTweets);
          
          if(data.likedTweets.includes(this.tweet.id)){
            this.isLikeed=true;
            console.info("is liked true");
          }
          else{
            this.isLikeed=false;
            console.info("is liked false");
          }
        },
        (error) => {}
      );

  }

  dataDiff(tweetDate: string | number | Date) {
    let firstDate = new Date(tweetDate);
    let currentDate = new Date().toISOString();
    let newDate = new Date(currentDate.toString());

    let firstDateinSecond = firstDate.getTime() / 1000;
    let secondDateinSecond = newDate.getTime() / 1000;
    let diff = Math.abs(firstDateinSecond - secondDateinSecond);

    if (diff < 60) {
      return Math.floor(diff) + ' seconds ago';
    } else if (diff < 3600) {
      return Math.floor(diff / 60) + ' minutes ago';
    } else if (diff < 86400) {
      return Math.floor(diff / 3600) + ' hours ago';
    } else {
      return firstDate.toDateString();
    }
  }

  

  like() {
    console.info("like fun called");
    this.isLikeed = !this.isLikeed;
    const userName = localStorage.getItem('userName');
    this.tweetService
      .likeUnLikeTweet(userName, this.tweet.id, this.isLikeed)
      .subscribe(
        (data) => {
          console.info("Tweet reply : ",data);
           this.count = data.like;
        },
        (error) => {}
      );
      this.userService
      .likeUnLikeTweetUser(userName, this.tweet.id, this.isLikeed)
      .subscribe(
        (data) => {
          console.info("Tweet reply : ",data.likedTweets);
          if(data.likedTweets.indexOf(this.tweet.id)>-1){
            this.isLikeed=true;
          }
          else
            this.isLikeed=false;
          
        },
        (error) => {}
      );
    console.info('clicked !! ' + this.isLikeed);

    let currentUrl = this.router.url;
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([currentUrl]);
        
  }

  replyTweet(){
    const tweetText = this.tweetField.value;
    console.info("Reply : "+tweetText);
    const userName =localStorage.getItem('userName');
    this.tweetService.replyToTweet(userName,this.tweet.id,tweetText).subscribe((data)=>{
        console.info("Tweet reply : ",data);
        this.addNewItem("new comment");
    },(errr)=>{

    })
    this.ngOnInit();
    this.tweetField.setValue('');

  }

  updateTweet(){
    const tweetText = this.editTweet.value;
    console.info("Updated tweet : "+tweetText);
    const userName =localStorage.getItem('userName');
    this.tweetService.updateTweet(userName,this.tweet.id,tweetText).subscribe((data)=>{
        console.info("Tweet reply : ",data);
        this.addNewItem("update tweet");
    },(errr)=>{

    })

    this.editTweet.setValue('');
    this.ngOnInit();
  }

  deleteTweet(){
   
    console.info(" tweet getting delete : ");
    const userName =localStorage.getItem('userName');
    this.tweetService.deleteTweet(this.tweet.id).subscribe((data)=>{
        console.info("Delete tweet : ",data);
        this.addNewItem("delete tweet");
    },(errr)=>{

    })

    
  }
  
  addNewItem(value: string) {
    this.newItemEvent.emit(value);
  }
}
