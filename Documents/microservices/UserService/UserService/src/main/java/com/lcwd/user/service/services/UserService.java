package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {
	//user operations
	
	//method for saving user in database
	User saveUser(User user);
	
	//get all users
	List<User> getAllUser();
	
	//get single user of a given user id
	User getUser(String userId);
	
	//TODO delete
	
	//TODO update
	
}
