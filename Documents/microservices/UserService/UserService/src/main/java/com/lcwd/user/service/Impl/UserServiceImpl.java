package com.lcwd.user.service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger =LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		String userId=UUID.randomUUID().toString();
		user.setUserId(userId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> allUsers= userRepository.findAll();
		
		for(User user:allUsers)
		{
			ArrayList<Rating> ratingOfUser= restTemplate.getForObject("http://localhost:8282/ratings/users/"+user.getUserId(), ArrayList.class);
			
			logger.info("ratings of the user: "+ratingOfUser.toString());
			
			user.setRatings(ratingOfUser);
			
		}
		
		return allUsers;
	}

	@Override
	public User getUser(String userId) {

		//get user from database using userId
		User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with given id not found!!"));
		//http://localhost:8282/ratings/users/11d9b61a-a71b-4139-b22c-f93bd27c255f
		
		//fetch rating of the above user from rating service
		
		Rating[] ratingOfUser= restTemplate.getForObject("http://RATING-MICROSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		
		//http://localhost:8181/hotels/e4dba0c7-258c-4925-9f5a-8fd4a9d058d9
		
		//fetch hotel details with the rating 
		
		
//		List<Rating> ratingList=ratingOfUser.stream().map(
//				);		
		
		for(int i=0;i<ratingOfUser.length;i++)
		{
//			ResponseEntity<Hotel> hotelOfRating= restTemplate.getForEntity("http://HOTEL-MICROSERVICE/hotels/"+ratingOfUser[i].getHotelId(), Hotel.class);
//			System.out.println("hotel of rating: "+hotelOfRating);
//			logger.info("hotel of the given rating: "+hotelOfRating.toString());
//			
//			ratingOfUser[i].setHotel(hotelOfRating.getBody());
			
			Hotel hotel=hotelService.getHotel(ratingOfUser[i].getHotelId());
			ratingOfUser[i].setHotel(hotel);
		}		
		
		List<Rating> finalRatingsOfUser=Arrays.stream(ratingOfUser).toList();
		logger.info("ratings of the user: "+ratingOfUser.toString());
		
		user.setRatings(finalRatingsOfUser);
		
		return user;
	}

}
