package com.tweetapp.tweets.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;


@Document
public class HashTag {

    @Id
    private String hashes;

    public String getHashes() {
        return hashes;
    }

    public void setHashes(String hashes) {
        this.hashes = hashes;
    }

    public HashTag(String hashes) {
        this.hashes = hashes;
    }

    @Override
    public String toString() {
        return "HashTag{" +
                "hashes='" + hashes + '\'' +
                '}';
    }
}
