package com.lcwd.rating.services;

import java.util.List;

import com.lcwd.rating.entities.Rating;

public interface RatingService {
	//create
	Rating createRating(Rating rating);
	
	//get all ratings
	List<Rating> getAllRatings();
	
	//get ratings by user id
	List<Rating> getRatingsByListUserId(String userId);
	
	//get all ratings of hotel
	List<Rating> getRatingsByListHotelId(String userId);
}
