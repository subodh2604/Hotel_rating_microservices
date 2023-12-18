package com.lcwd.hotel.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.exceptions.ResourceNotFoundException;
import com.lcwd.hotel.repositories.HotelRepository;
import com.lcwd.hotel.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel saveHotel(Hotel hotel) {
		
		String hotelId=UUID.randomUUID().toString();
		hotel.setId(hotelId);
;		Hotel hotel2=hotelRepository.save(hotel);
		return hotel2;
	}

	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> hotels=hotelRepository.findAll();
		return hotels;
	}

	@Override
	public Hotel getSingleHotel(String id) {
		return hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel with given id not found!!"));
	}

}
