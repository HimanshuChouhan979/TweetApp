package com.tweetapp.tweets;


import com.tweetapp.tweets.model.Comment;
import com.tweetapp.tweets.model.Tweet;
import com.tweetapp.tweets.repository.TweetRepo;
import com.tweetapp.tweets.services.TweetServiceImpl;
import com.tweetapp.tweets.utility.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TweetServiceImplTest {
    @Mock
    TweetRepo tweetRepo;




    @InjectMocks
    TweetServiceImpl tweetService;

    @Test
    public void getAllTweets(){

        List<Tweet> uList = new ArrayList<>();
        Tweet u = new Tweet();
        u.setTweetMsg("Hi there !!");
        uList.add(u);
        when(tweetRepo.findAll()).thenReturn(uList);
        List<Tweet> userList= tweetService.getAllTweets();
        assertEquals(1,userList.size());
    }

    @Test
    public void getTweetByUserId() {
        List<Tweet> tweetlist = new ArrayList<>();
        Tweet u = new Tweet();
        u.setTweetMsg("Hi there !!");
        u.setId("ww");
        tweetlist.add(u);
        when(tweetRepo.findByUserId(anyString())).thenReturn(tweetlist);
        List<Tweet> userList= tweetService.getTweetByUserId("swankhade");
        assertEquals(1,userList.size());
    }

    @Test(expected = NullPointerException.class)
    public void postTweet() {
        /*User user = new User();
        user.setEmail("s@gmail.com");
        user.setlogInId("swankhade");

        when(userRepoImpl.findById("swankhade")).thenReturn(Optional.of(user));
        Optional<User> userInfo = userRepoImpl.findById("swankhade");*/

        Tweet tweetObj = new Tweet();
        tweetObj.setTweetMsg("hi");
        tweetObj.setFirstName("shivam");
        tweetObj.setLastName("wankhade");
        //LocalDateTime time = LocalDateTime.now();
        tweetObj.setTime(new Date());
        tweetObj.setLike(String.valueOf(0));
        tweetObj.setCommentList(new ArrayList<>());
        tweetObj.setUserId("swankhade");

//        when(tweetRepo.insert(tweetObj)).thenReturn(tweetObj);

        String success= tweetService.postTweet("swankhade",tweetObj);
        assertEquals(Constants.SUCCESS,success);
    }

    @Test
    public void updateTweetException() {

        Tweet newTweet = new Tweet();
        Tweet tweetObj = new Tweet();
        tweetObj.setTweetMsg("hi");
        tweetObj.setFirstName("somu");
        tweetObj.setLastName("panda");
        //LocalDateTime time = LocalDateTime.now();
        tweetObj.setTime(new Date());
        tweetObj.setLike(String.valueOf(0));
        tweetObj.setCommentList(new ArrayList<>());
        tweetObj.setUserId("spanda");


//        when(tweetRepo.save(tweetObj)).thenReturn(tweetObj);
        Tweet tweet = tweetService.updateTweet("spanda","123","spanda here");

        assertEquals(tweet.getFirstName(),null);
    }

    @Test
    public void updateTweet() {

        Tweet tweetObj = new Tweet();
        tweetObj.setTweetMsg("hi");
        tweetObj.setFirstName("ad");
        tweetObj.setLastName("dee");
        tweetObj.setId("123");
        //LocalDateTime time = LocalDateTime.now();
        tweetObj.setTime(new Date());
        tweetObj.setLike(String.valueOf(0));
        tweetObj.setCommentList(new ArrayList<>());
        tweetObj.setUserId("spanda");
//
//        when(tweetRepo.findById("ad")).thenReturn(Optional.of(tweetObj));
//        when(tweetRepo.save(tweetObj)).thenReturn(tweetObj);
        Tweet tweet = tweetService.updateTweet("ad","123","ad here");

        assertEquals(tweet.getFirstName(),null);
    }

    @Test
    public void deleteTweet() {

        Tweet newTweet = new Tweet();
        Tweet tweetObj = new Tweet();
        tweetObj.setTweetMsg("hi");
        tweetObj.setFirstName("somu");
        tweetObj.setLastName("panda");
        //LocalDateTime time = LocalDateTime.now();
        tweetObj.setTime(new Date());
        tweetObj.setLike(String.valueOf(0));
        tweetObj.setCommentList(new ArrayList<>());
        tweetObj.setUserId("spanda");
        when(tweetRepo.findById("123")).thenReturn(Optional.of(newTweet));
        tweetRepo.deleteById("123");
        boolean b = tweetService.deleteTweet("123");

        assertEquals(false,b);
    }
    @Test
    public void deleteTweetFalse() {

        when(tweetRepo.findById("123")).thenReturn(Optional.of(new Tweet()));
        when(tweetRepo.findById("123")).thenReturn(Optional.of(new Tweet()));
        boolean b = tweetService.deleteTweet("123");

        assertEquals(false,b);
    }



    @Test
    public void likeTweet() {

        Tweet tweetObj = new Tweet();
        tweetObj.setTweetMsg("hi");
        tweetObj.setFirstName("somu");
        tweetObj.setLastName("panda");
        //LocalDateTime time = LocalDateTime.now();
        tweetObj.setTime(new Date());
        tweetObj.setLike(String.valueOf(0));
        tweetObj.setCommentList(new ArrayList<>());
        tweetObj.setUserId("spanda");
        tweetObj.setId("123");
        when(tweetRepo.findById("123")).thenReturn(Optional.of(tweetObj));
        when(tweetRepo.save(tweetObj)).thenReturn(tweetObj);

        Set<String> exp = new HashSet<>();
        exp.add("spanda");
        Set<String> list= tweetService.likeTweet("spanda","123","true");
        assertEquals(exp,list);


    }

    @Test
    public void likeTweetFalse() {

        Tweet tweetObj = new Tweet();
        tweetObj.setTweetMsg("hi");
        tweetObj.setFirstName("somu");
        tweetObj.setLastName("panda");
        //LocalDateTime time = LocalDateTime.now();
        tweetObj.setTime(new Date());
        tweetObj.setLike(String.valueOf(0));
        tweetObj.setCommentList(new ArrayList<>());
        tweetObj.setUserId("spanda");
        tweetObj.setId("123");
        when(tweetRepo.findById("123")).thenReturn(Optional.of(tweetObj));
        when(tweetRepo.save(tweetObj)).thenReturn(tweetObj);

        Set<String> exp = new HashSet<>();
        Set<String> list= tweetService.likeTweet("spanda","123","false");
        assertEquals(exp,list);
    }

    @Test
    public void replyTweet() {

        Tweet tweetObj = new Tweet();
        tweetObj.setTweetMsg("hi");
        tweetObj.setFirstName("somu");
        tweetObj.setLastName("panda");
        //LocalDateTime time = LocalDateTime.now();
        tweetObj.setTime(new Date());
        tweetObj.setLike(String.valueOf(0));
        tweetObj.setCommentList(new ArrayList<>());
        tweetObj.setUserId("spanda");
        tweetObj.setId("123");
        when(tweetRepo.findById("123")).thenReturn(Optional.of(tweetObj));

        List<Comment> commentList = new ArrayList<>();
        tweetObj.setCommentList(commentList);
        when(tweetRepo.save(tweetObj)).thenReturn(tweetObj);

        Tweet newTweet = tweetService.replyTweet("spanda","123","spmu is replying to tweet");

        assertEquals(1,newTweet.getCommentList().size());
    }

}
