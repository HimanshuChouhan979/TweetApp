import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HashTag } from 'src/app/common/interface/hashtag';
import { TweetDetails } from 'src/app/common/interface/tweet-details';
import { UserDetails } from 'src/app/common/interface/user-details';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  constructor(private http:HttpClient) { }

  url = environment.commonUrlTweets

  postTweet(userName: any,payload: any){
    return this.http.post<String>(this.url + "/"+userName +'/add', payload, {
      responseType: 'text' as 'json'
    });
  }

  getAllTweet(){
    return this.http.get<TweetDetails>(this.url + '/all');
  }

  likeUnLikeTweet(userName: string | null,tweetId: string,type: string | boolean){
    return this.http.post<TweetDetails>(this.url + '/like/' +userName +'/'+tweetId + '/' + type,null);
  }

  getAllTweetByUserId(userName: any){
    return this.http.get<TweetDetails[]>(this.url +'/'+userName);

  }
  replyToTweet(userName: string | null,tweetId: string,replyMsg: any){
    return this.http.post<any>(this.url + '/reply/' +userName +'/'+tweetId , replyMsg);
  }

  updateTweet(userName: string | null,tweetId: string,replyMsg: any){
    return this.http.put<UserDetails>(this.url + '/update/' +userName +'/'+tweetId , replyMsg);
  }

  getAllHashtags(){
    return this.http.get<HashTag[]>(this.url + '/hashtag');
  }

  deleteTweet(tweetId: string){
    return this.http.delete<any>(this.url + '/delete' +'/'+tweetId);  
  }
}
