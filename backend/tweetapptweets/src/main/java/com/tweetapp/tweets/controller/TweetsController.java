package com.tweetapp.tweets.controller;

import com.tweetapp.tweets.clients.AuthClient;
import com.tweetapp.tweets.exceptions.PostTweetFailedException;
import com.tweetapp.tweets.model.AuthResponse;
import com.tweetapp.tweets.model.HashTag;
import com.tweetapp.tweets.model.Tweet;
import com.tweetapp.tweets.services.TweetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1.0/tweets")
public class TweetsController {

    @Autowired
    AuthClient auth;


    @Autowired
    TweetServiceImpl tweetService;

    @RequestMapping("/welcome")
    public String welcome(@RequestHeader(name="Authorization") String token) {
        AuthResponse response = auth.verifyToken(token);
        if (response.isValid()) {

            return "Welcome to tweet app";
        }
        else return "Invalid user";
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<String> postTweet(@PathVariable("userId") String id,@RequestBody Tweet tweet){
        try {
            return new ResponseEntity(tweetService.postTweet(id, tweet), HttpStatus.OK);
        }catch (PostTweetFailedException e){
            return new ResponseEntity("Tweet posting failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<Tweet> getAllTweets() {
        return tweetService.getAllTweets();
    }

    @GetMapping("/hashtag")
    public List<HashTag> getAllHashTag() {

        return tweetService.getAllHashTags();
    }

    @GetMapping("/{userName}")
    public List<Tweet> getTweetsByUserId(@PathVariable("userName") String userId) {
        return tweetService.getTweetByUserId(userId);
    }

    @PutMapping("/update/{userId}/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable("userId") String userId,@PathVariable("id") String id,@RequestBody String updatedMsg) {
        return  new ResponseEntity(tweetService.updateTweet(userId,id,updatedMsg),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTweet(@PathVariable("id") String id) {

        return new ResponseEntity(tweetService.deleteTweet(id),HttpStatus.OK);
    }

    @PostMapping("/like/{userId}/{id}/{type}")
    public ResponseEntity<List<String>> likeTweet(@PathVariable("userId") String userId ,@PathVariable("id") String tweetId,@PathVariable("type") String type){
        return new ResponseEntity(tweetService.likeTweet(userId,tweetId,type),HttpStatus.OK);
    }

    @PostMapping("/reply/{userId}/{id}")
    public ResponseEntity replyTweet(@PathVariable("userId") String userId,@PathVariable("id") String id,@RequestBody String reply){
        return new ResponseEntity(tweetService.replyTweet(userId, id, reply),HttpStatus.OK);
    }


}
