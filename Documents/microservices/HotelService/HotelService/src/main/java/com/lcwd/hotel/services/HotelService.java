package com.lcwd.hotel.services;

import java.util.List;

import com.lcwd.hotel.entities.Hotel;

public interface HotelService {
	//create 
	Hotel saveHotel(Hotel hotel);
	
	//get all
	List<Hotel> getAllHotels();
	
	//get single
	Hotel getSingleHotel(String id);
}
