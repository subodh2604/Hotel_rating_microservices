package com.lcwd.hotel.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	String messageString;
	public ResourceNotFoundException()
	{
		super();
	}
	
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
