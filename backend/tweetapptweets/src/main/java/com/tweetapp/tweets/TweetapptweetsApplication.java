package com.tweetapp.tweets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TweetapptweetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetapptweetsApplication.class, args);
	}

}
