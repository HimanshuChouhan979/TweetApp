package com.tweetapp.tweets.repository;


import com.tweetapp.tweets.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepo extends MongoRepository<Tweet, String> {

    List<Tweet> findByUserId(String userId);
}
