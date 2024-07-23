package com.vehicleRental.service;

import com.vehicleRental.model.User;

public interface UserService {
	public User validateUser(User user);
	public User addUser(User user);
	public User removeUser(User user);
	//User signOut(User user);
	public User getPassword(String userId);
}
