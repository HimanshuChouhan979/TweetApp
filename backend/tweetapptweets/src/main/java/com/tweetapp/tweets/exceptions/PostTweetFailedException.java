package com.tweetapp.tweets.exceptions;

public class PostTweetFailedException extends RuntimeException{

    public PostTweetFailedException(String s) {
        super(s);
    }
}
