import { Component, OnInit } from '@angular/core';
import { TweetService } from 'src/app/services/tweetservice/tweet.service';

@Component({
  selector: 'app-hashtags',
  templateUrl: './hashtags.component.html',
  styleUrls: ['./hashtags.component.css']
})
export class HashtagsComponent implements OnInit {

  constructor(private tweetservice:TweetService) { }
  hashtagList:any


  ngOnInit(): void {
    this.tweetservice.getAllHashtags().subscribe((data)=>{
      this.hashtagList=data;
      
      console.log("hashtag list initial ",this.hashtagList);
  },(error)=>{})
  }
  

}
