package com.tweetapp.users.servicesImpl;

import com.mongodb.MongoWriteException;

import com.tweetapp.users.exceptions.*;
import com.tweetapp.users.model.UserLoginCredential;
import com.tweetapp.users.model.UserToken;
import com.tweetapp.users.model.Users;
import com.tweetapp.users.repository.UsersDao;
import com.tweetapp.users.repository.UsersRepo;
import com.tweetapp.users.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

@Service
public class UsersServiceImpl implements UserDetailsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    EmailService emailService;


    public ResponseEntity registerNewUser(Users resgistrationDetails) {
        Optional<Users> ifUsernameExists = usersRepo.findByEmail(resgistrationDetails.getUserName());
        if(ifUsernameExists.isPresent()) throw new UsernameExistsException(Constants.USER_ID_IS_EXISTS);
        Optional<Users> ifUserExists = usersRepo.findByEmail(resgistrationDetails.getEmail());

        if(ifUserExists.isPresent()) throw new EmailIdExistsException(Constants.EMAIL_ID_EXITS);
        
        try{
            usersRepo.insert(resgistrationDetails);

        }catch (DuplicateKeyException e){
                throw new UsernameExistsException(e.getMessage());
        }
        catch (MongoWriteException e){
            LOGGER.debug("Exception in user insertion "+e.getMessage());
        }
        return new ResponseEntity(resgistrationDetails,HttpStatus.OK);
    }


    public boolean forgotPassword(String email) throws MessagingException, IOException {

        Optional<Users> userInfo =usersRepo.findByEmail(email);
        if(userInfo.isPresent()){
            Users u =  userInfo.get();
            Random random =new Random(1000);
            int otp = random.nextInt(999999);
            emailService.sendmail(email,otp);
            System.out.println("OTP "+otp);
            u.setOtp(String.valueOf(otp));
            usersRepo.save(u);

            return true;
        }else{
            throw new EmailIdExistsException("Email not registered");
        }
    }

    public boolean verifyOtp(UserToken otpToken) {
        Optional<Users> user =  usersRepo.findByEmail(otpToken.getUid());
        if((user.get().getOtp()).compareToIgnoreCase(otpToken.getAuthToken())==0){
            LOGGER.debug("OTP matched");
            return true;
        }
        else {
            user.get().setOtp(null);
            throw new OtpVerificationFailedException("Invalid OTP");
        }
    }

    public ResponseEntity changePassword(UserLoginCredential changedCreds) {

            Optional<Users> u =  usersRepo.findByEmail(changedCreds.getUid());
            Users newDetails=u.get();
            newDetails.setPassword(changedCreds.getPassword());
            newDetails.setOtp(null);

            return new ResponseEntity(usersRepo.save(newDetails),HttpStatus.OK);

    }


    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        Optional<Users> user = usersRepo.findById(uid);
        return new User(user.get().getUserName(), user.get().getPassword(), new ArrayList<>());
    }


    public Users getUserDetails(String userName) {
        Optional<Users> user = usersRepo.findById(userName);
        if(user.isPresent())
        {
            Users users=user.get();
            System.out.println(users.toString());
            return users;
        }
        else
            throw new UsernameNotFoundException("Unable to get user details for "+userName);
    }

    public List<Users> getAllUsers() {

        List<Users> userList= usersRepo.findAll();
        Collections.sort(userList, (Users u1, Users u2) -> u1.getUserName().compareTo(u2.getUserName()) );

        for (Users u: userList
        ) {
            LOGGER.info("Tweet : {}",u.toString());
        }
        return userList;
    }


    public Users likeTweet(String userId, String id, String type) {
        Optional<Users> user = usersRepo.findById(userId);

        Users newUser = user.get();
        Set<String> likedTweets = new HashSet<>();
        likedTweets = newUser.getLikedTweets();

        if (type.equals("false")) {
            likedTweets.remove(id);
        } else {
            likedTweets.add(id);
        }
        newUser.setLikedTweets(likedTweets);
        usersRepo.save(newUser);
        return newUser;

    }
}
