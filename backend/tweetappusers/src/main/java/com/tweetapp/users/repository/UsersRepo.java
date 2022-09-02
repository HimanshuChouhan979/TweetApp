package com.tweetapp.users.repository;



import com.tweetapp.users.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends MongoRepository<Users, String> {

    Optional<Users> findByEmail(String emailId);
    Optional<Users> findById(String userName);


}