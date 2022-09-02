package com.tweetapp.users.controller;



import com.tweetapp.users.model.AuthResponse;
import com.tweetapp.users.model.UserLoginCredential;
import com.tweetapp.users.model.UserToken;
import com.tweetapp.users.model.Users;
import com.tweetapp.users.repository.UsersDao;
import com.tweetapp.users.repository.UsersRepo;

import com.tweetapp.users.servicesImpl.JwtUtil;
import com.tweetapp.users.servicesImpl.UsersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping ("/api/v1.0/tweets")
public class UsersController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    UsersServiceImpl usersServiceImpl;
    @Autowired
    private AuthenticationManager authmanager;
    @Autowired
    private JwtUtil jwtutil;
    @Autowired
    private UsersRepo users;


    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserLoginCredential userlogincredentials) {

        try {
            authmanager.authenticate(new UsernamePasswordAuthenticationToken(userlogincredentials.getUid(), userlogincredentials.getPassword()));

        } catch (Exception e) {
            return new ResponseEntity<>("Invalid UserId/Password", HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userdetails = usersServiceImpl.loadUserByUsername(userlogincredentials.getUid() + "");
        return new ResponseEntity<>(new UserToken(userlogincredentials.getUid(), jwtutil.generateToken(userdetails)), HttpStatus.OK);


    }

    @RequestMapping(value = "/auth/validate", method = RequestMethod.GET)
    public ResponseEntity<?> getValidity(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        AuthResponse res = new AuthResponse();
        if (jwtutil.validateToken(token)) {
            res.setUid(jwtutil.extractUsername(token));
            res.setValid(true);
            res.setName(users.findById(res.getUid()).get().getUserName());
        } else
            res.setValid(false);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody Users details) {
        return new ResponseEntity(usersServiceImpl.registerNewUser(details), HttpStatus.OK);
    }

    @PostMapping("/forgot")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody String email) throws MessagingException, IOException {
        return new ResponseEntity(usersServiceImpl.forgotPassword(email), HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyOtp(@RequestBody UserToken otptoken) throws MessagingException, IOException {
        return new ResponseEntity(usersServiceImpl.verifyOtp(otptoken), HttpStatus.OK);
    }

    @PostMapping("/change")
    public ResponseEntity<Boolean> changePassword(@RequestBody UserLoginCredential changedPassword) {
        return new ResponseEntity(usersServiceImpl.changePassword(changedPassword), HttpStatus.OK);
    }

    @GetMapping("/getuser/{userName}")
    public Users getUser(@PathVariable("userName") String userName){
        return usersServiceImpl.getUserDetails(userName);
    }

    @GetMapping("/users/all")
    public List<Users> getAllUsers() {
        return usersServiceImpl.getAllUsers();
    }


    @PostMapping("/like/{userId}/{id}/{type}")
    public Users likeTweet(@PathVariable("userId") String userId ,@PathVariable("id") String tweetId,@PathVariable("type") String type){
        return usersServiceImpl.likeTweet(userId,tweetId,type);
    }

}
