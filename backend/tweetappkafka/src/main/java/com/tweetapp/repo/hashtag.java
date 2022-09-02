package com.tweetapp.repo;

import com.tweetapp.model.HashTag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface hashtag extends MongoRepository<HashTag, String> {

    Optional<String> findByHashes(String hashtag);

}
