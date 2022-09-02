package com.tweetapp.services;

import com.tweetapp.model.HashTag;
import com.tweetapp.repo.hashtag;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ConsumerClass {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerClass.class);

    @Autowired
    hashtag hashtagRepo;

    @KafkaListener(topics = "user")
    public void consume(String message){
        logger.info(String.format("*******Consumer just received the message",message));
        Optional<String> existingHashes = hashtagRepo.findByHashes(message);

        if(existingHashes.isEmpty() || !message.equalsIgnoreCase(existingHashes.get()))
        {
            HashTag hashTag =new HashTag(message);
            hashtagRepo.save(hashTag);
        }

    }

}
