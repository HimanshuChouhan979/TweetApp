package com.tweetapp.tweets.repository;


import com.tweetapp.tweets.model.HashTag;
import com.tweetapp.tweets.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HashtagRepo extends MongoRepository<HashTag, String> {


}
