package com.tweetapp.users.exceptions;

public class OtpVerificationFailedException extends RuntimeException{

    public OtpVerificationFailedException(String s) {
        super(s);
    }
}
