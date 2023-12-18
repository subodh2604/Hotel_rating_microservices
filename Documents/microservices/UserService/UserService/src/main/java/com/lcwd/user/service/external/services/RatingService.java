package com.lcwd.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lcwd.user.service.entities.Rating;

@FeignClient(name = "RATING-MICROSERVICE")
public interface RatingService {
	
	@PostMapping("/ratings")
	public Rating createRating(@RequestBody Rating rating);
}
