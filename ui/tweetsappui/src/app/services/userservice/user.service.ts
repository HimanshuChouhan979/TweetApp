import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDetails } from 'src/app/common/interface/user-details';
import { environment } from 'src/environments/environment.prod';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  
 

  url = environment.commonUrl;
  url2 = environment.commonUrlTweets;

  constructor(private http:HttpClient) { }

  setUserName(userName:any){
    console.log("setting username");
    localStorage.setItem("userName",userName);
  }

  setFirstName(firstName:any){
    console.log("setting firstname");
    localStorage.setItem("firstName",firstName);
  }

  likedTweets(likedTweets: string[]) {
    console.log("setting liked tweets");
    localStorage.setItem("likedTweets",JSON.stringify(likedTweets));
  }

  setLastName(lastName:any){
    console.log("setting lastname");
    localStorage.setItem("lastName",lastName);
  }

getUser(userName:any){ 
 console.log("get username service called "+userName);
  return this.http.get<UserDetails>(this.url+"/getuser/"+userName)
}

getWelcome(){
  return this.http.get(this.url2+"/welcome",{
    withCredentials:true
  });
}

likeUnLikeTweetUser(userName: string | null,tweetId: string,type: string | boolean){
  return this.http.post<UserDetails>(this.url + '/like/' +userName +'/'+tweetId + '/' + type,null);
}

// saveUserDetails(user:any){
//   localStorage.setItem("user",user);
// }

getAllUser(){
  return this.http.get<UserDetails[]>(this.url + '/users/all')
}

}
