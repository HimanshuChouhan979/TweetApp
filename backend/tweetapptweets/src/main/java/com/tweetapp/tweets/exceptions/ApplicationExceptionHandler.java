package com.tweetapp.tweets.exceptions;


import com.tweetapp.tweets.utility.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(PostTweetFailedException.class)
    public ResponseEntity resoureNotFoundException(PostTweetFailedException exception){
        return new ResponseEntity(new ErrorMessage(Constants.ERROR,Constants.RESOURSE_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }


}
