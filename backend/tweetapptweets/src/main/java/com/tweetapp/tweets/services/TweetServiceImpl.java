package com.tweetapp.tweets.services;


import com.tweetapp.tweets.exceptions.PostTweetFailedException;
import com.tweetapp.tweets.model.Comment;
import com.tweetapp.tweets.model.HashTag;
import com.tweetapp.tweets.model.Tweet;
import com.tweetapp.tweets.repository.HashtagRepo;
import com.tweetapp.tweets.repository.TweetRepo;
import com.tweetapp.tweets.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TweetServiceImpl {

    public static final Logger LOGGER = LoggerFactory.getLogger(TweetServiceImpl.class);


    @Autowired
    TweetRepo tweetRepository;

    @Autowired
    HashtagRepo hashtagRepo;


    public String postTweet(String userId, Tweet tweet) {

        Tweet tweetObj = new Tweet();
        tweetObj.setTweetMsg(tweet.getTweetMsg());
        tweetObj.setFirstName(tweet.getFirstName());
        tweetObj.setLastName(tweet.getLastName());
        tweetObj.setTime(new Date());
        tweetObj.setLike(String.valueOf(0));
        tweetObj.setCommentList(new ArrayList<>());
        tweetObj.setUserId(userId);

        String[] hashtag=tweet.getTweetMsg().split("#");
        for(int i=1;i<hashtag.length; i++){
            List<HashTag> hashTag = hashtagRepo.findAll();
            if(!hashTag.contains(hashtag[i].toUpperCase(Locale.ENGLISH))){
                hashtagRepo.save(new HashTag(hashtag[i].toLowerCase(Locale.ENGLISH)));
            }

        }

        LOGGER.info("Tweet {}",tweetObj.toString());
        Tweet tObj = tweetRepository.insert(tweetObj);

        if(!tObj.getId().isEmpty()){
            return Constants.SUCCESS;
        }
        throw new PostTweetFailedException("Tweet posting failed");
    }


    public List<Tweet> getAllTweets() {
        List<Tweet> tweetList=null;
        tweetList = tweetRepository.findAll();
        Collections.sort(tweetList);

        for (Tweet t: tweetList
             ) {
            LOGGER.info("Tweet : {}",t.toString());
        }
        return tweetList;
    }


    public List<Tweet> getTweetByUserId(String userId) {
        List<Tweet> tweetlist = null;
        tweetlist = tweetRepository.findByUserId(userId);
        Collections.sort(tweetlist);
        return tweetlist;
    }


    public  Tweet updateTweet(String userId, String id, String updatedMsg) {
        LOGGER.info("Inside update tweet method");
        Optional<Tweet> tweet = tweetRepository.findById(id);
        Tweet newTweet = new Tweet();
        if(tweet.isPresent() && tweet.get().getId()!=null){
           newTweet = tweet.get();
           newTweet.setTweetMsg(updatedMsg);
           newTweet.setTime(new Date());

           newTweet = tweetRepository.save(newTweet);
            System.out.println("Updated successfullly");
        }
        return newTweet;
    }


    public boolean deleteTweet(String id) {

        Optional<Tweet> tweet = tweetRepository.findById(id);
        if(tweet.isPresent() && tweet.get().getId()!=null){
            tweetRepository.deleteById(id);
            return true;
        }
        return false;}

    public Set<String> likeTweet(String userId, String id, String type) {
        Optional<Tweet> tweet = tweetRepository.findById(id);

        Tweet newTweet = new Tweet();
        Set<String> likeByUser = new HashSet<>();

        if(tweet.isPresent() && tweet.get().getId()!=null){
            newTweet = tweet.get();
            likeByUser =  newTweet.getLikeBy();
            if(type.contains("true")){
              likeByUser.add(userId);

            }else{
                likeByUser.remove(userId);
            }
            newTweet.setLike(String.valueOf(likeByUser.size()));
            newTweet.setLikeBy(likeByUser);
            tweetRepository.save(newTweet);

            return newTweet.getLikeBy();
        }
        return newTweet.getLikeBy();
    }


    public Tweet replyTweet(String userId, String id, String reply) {
        LOGGER.info("Service : {} and Reply {} ",id,reply);
        Optional<Tweet> tweet = tweetRepository.findById(id);

        Tweet newTweet = tweet.get();
        if(tweet.isPresent() && tweet.get().getId()!=null){
            List<Comment> commentList = new ArrayList<>();
            commentList = newTweet.getCommentList();

            Comment replyToTweet = new Comment();
            replyToTweet.setText(reply);
            replyToTweet.setCommentBy(userId);


            if(tweet.get().getCommentList() == null || tweet.get().getCommentList().size() ==0){
                commentList.add(replyToTweet);
                newTweet.setCommentList(commentList);
            }else{

                commentList.add(replyToTweet);
                newTweet.setCommentList(commentList);

            }
            newTweet = tweetRepository.save(newTweet);
            LOGGER.info("Updated successfullly {}",newTweet.toString());
        }
        return newTweet;
    }

    public List<HashTag> getAllHashTags() {
        List<HashTag> hashTagList = hashtagRepo.findAll();
        return hashTagList;
    }
}
