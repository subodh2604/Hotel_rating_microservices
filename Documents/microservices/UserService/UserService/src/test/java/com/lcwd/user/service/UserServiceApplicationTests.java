package com.lcwd.user.service;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.external.services.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void createRating()
	{
		Rating rating=Rating.builder().rating("10").userId("").hotelId("").feedback("this is created using feign client").build();
		Rating savedRating= ratingService.createRating(rating);
		
		System.out.println("rating is created");
	}
}
