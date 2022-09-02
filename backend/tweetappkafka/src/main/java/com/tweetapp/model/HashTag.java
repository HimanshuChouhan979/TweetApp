package com.tweetapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
@Document
public class HashTag {

     String hashes;

        public String getHashes() {
                return hashes;
        }

        public void setHashes(String hashes) {
                this.hashes = hashes;
        }

        @Override
        public String toString() {
                return "HashTag{" +
                        "hashes='" + hashes + '\'' +
                        '}';
        }

    public HashTag(String hashes) {
        this.hashes = hashes;
    }
}
