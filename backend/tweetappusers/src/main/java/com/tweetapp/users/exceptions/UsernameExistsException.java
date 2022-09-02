package com.tweetapp.users.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(String message){super(message);}
}
