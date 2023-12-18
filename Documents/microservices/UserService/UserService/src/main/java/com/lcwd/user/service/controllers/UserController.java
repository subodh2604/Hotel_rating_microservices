package com.lcwd.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.Retry;

@RestController
@RequestMapping("users") //when users is hitted in url, this class object will get created
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//create
	//@RequestBody deserializes json object into java object
	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User user1=userService.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	int count=1;
	
	//single user get
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
//	@io.github.resilience4j.retry.annotation.Retry(name = "ratingHotelRetry",fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId)
	{
		System.out.println("Retry count "+count);
		count++;
		User user=userService.getUser(userId);
		
		return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<User> ratingHotelFallback( String userId,Exception ex)
	{
		System.out.println("service is down: "+ex.getMessage());
		User user=User.builder().name("dummy").about("service is down").email("dummy@gmail.com").build();
		
		return ResponseEntity.ok(user);
	}
	
	//all user get
	@GetMapping()
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> users=userService.getAllUser();
		
		return ResponseEntity.ok(users);
	}
}
