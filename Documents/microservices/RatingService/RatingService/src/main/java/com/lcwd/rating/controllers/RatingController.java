package com.lcwd.rating.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.rating.entities.Rating;
import com.lcwd.rating.services.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping()
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		
		Rating rating1= ratingService.createRating(rating);
		
		return new ResponseEntity<Rating>(rating1,HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Rating>> getAllRatings() {
		
		return ResponseEntity.ok(ratingService.getAllRatings());
	}
	
	@GetMapping("users/{userId}")
	public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable("userId") String userId) {
		
		return ResponseEntity.ok(ratingService.getRatingsByListUserId(userId));
	}
	
	@GetMapping("hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable("hotelId") String hotelId) {
		
		return ResponseEntity.ok(ratingService.getRatingsByListHotelId(hotelId));
	}

}
