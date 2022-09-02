package com.tweetapp.users.exceptions;


import com.tweetapp.users.utility.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resoureNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity(new ErrorMessage(Constants.ERROR,Constants.RESOURSE_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFoundException(UserNotFoundException exception){
        return new ResponseEntity(new ErrorMessage(Constants.ERROR,Constants.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity userNameAlreadyExistsException(UsernameExistsException exception){
        return new ResponseEntity(new ErrorMessage(Constants.ERROR,Constants.USER_ID_IS_EXISTS), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity InvalidInputException(InvalidInputException exception){
        return new ResponseEntity(new ErrorMessage(Constants.ERROR,Constants.INVALID_INPUT), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailIdExistsException.class)
    public ResponseEntity EmailIdAlreadyExitsException(EmailIdExistsException exception){
        return new ResponseEntity(new ErrorMessage(Constants.ERROR,exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OtpVerificationFailedException.class)
    public ResponseEntity OtpVerificationFailedException(OtpVerificationFailedException exception){
        return new ResponseEntity(new ErrorMessage(Constants.ERROR,exception.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
