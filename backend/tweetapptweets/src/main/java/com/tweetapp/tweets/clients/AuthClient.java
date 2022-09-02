package com.tweetapp.tweets.clients;

import com.tweetapp.tweets.model.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;



@FeignClient(url="http://localhost:8093/api/v1.0/tweets/auth",name="AUTH")
public interface AuthClient {

	@GetMapping("/validate")
	public AuthResponse verifyToken(@RequestHeader(name="Authorization",required=true)String token);

	
}
