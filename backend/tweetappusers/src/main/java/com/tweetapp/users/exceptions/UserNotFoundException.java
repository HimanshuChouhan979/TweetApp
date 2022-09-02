package com.tweetapp.users.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){super(message);}
}
