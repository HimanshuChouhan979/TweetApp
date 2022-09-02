package com.tweetapp.users;


import com.tweetapp.users.exceptions.EmailIdExistsException;
import com.tweetapp.users.exceptions.UserNotFoundException;
import com.tweetapp.users.model.Users;
import com.tweetapp.users.repository.UsersRepo;
import com.tweetapp.users.servicesImpl.UsersServiceImpl;
import com.tweetapp.users.utility.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UsersRepo userRepoImpl;

    @InjectMocks
    UsersServiceImpl userServiceimpl;

    @Test
    public void getAllUsers(){

        List<Users> uList = new ArrayList<>();
        Users u = new Users();
        u.setUserName("swankhade");
        u.setEmail("s@gmail.com");
        uList.add(u);
        when(userRepoImpl.findAll()).thenReturn(uList);
        List<Users> userList= userServiceimpl.getAllUsers();
        assertEquals(1,userList.size());

    }

    @Test(expected = NullPointerException.class)
    public void getAllUsersEmpty(){

        List<Users> uList = null;
        when(userRepoImpl.findAll()).thenReturn(uList);
        List<Users> userList= userServiceimpl.getAllUsers();
    }

    @Test(expected = NullPointerException.class)
    public void getUserByIdException(){
        Optional<Users> user = null;
        when(userRepoImpl.findById("swankhade")).thenReturn(user);
        Users u = userServiceimpl.getUserDetails("swankhade");

    }

    @Test
    public void getUserById(){
        Users user = new Users();
        user.setEmail("s@gmail.com");
        user.setUserName("swankhade");

        when(userRepoImpl.findById("swankhade")).thenReturn(Optional.of(user));
        Users u = userServiceimpl.getUserDetails("swankhade");

        assertEquals("swankhade",u.getUserName());
    }


    @Test
    public void registerNewUser(){
        Users resgistrationDetails = new Users();
        ResponseEntity flag = userServiceimpl.registerNewUser(resgistrationDetails);
        assertEquals(HttpStatus.OK,flag.getStatusCode());
    }


    @Test(expected = EmailIdExistsException.class)
    public void registerNewUserException(){
        Users resgistrationDetails = new Users();
        resgistrationDetails.setUserName("swankhade");
        resgistrationDetails.setEmail("s@gmail.com");
        Optional<Users> isuserExits = Optional.of(new Users());
        when(userRepoImpl.findByEmail("s@gmail.com")).thenReturn(isuserExits);
        ResponseEntity flag = userServiceimpl.registerNewUser(resgistrationDetails);
        assertEquals(Constants.SUCCESS,flag);
    }


}
