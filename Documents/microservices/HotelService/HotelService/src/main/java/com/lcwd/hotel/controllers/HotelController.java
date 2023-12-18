package com.lcwd.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PostMapping()
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		Hotel hotel1=hotelService.saveHotel(hotel);
		
		return new ResponseEntity<Hotel>(hotel1,HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Hotel>> getAllHotels()
	{
		List<Hotel> hotels=hotelService.getAllHotels();
		
		return ResponseEntity.ok(hotels);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getSingleHotels(@PathVariable("id") String id)
	{
		Hotel hotel=hotelService.getSingleHotel(id);
		
		return ResponseEntity.ok(hotel);
	}
}
