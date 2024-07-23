package com.vehicleRental.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicleRental.model.User;
import com.vehicleRental.repository.UserRepository;
import com.vehicleRental.service.UserService;

@Service
public class UserImpl implements UserService {
	@Autowired
	private UserRepository ur;

	@Override
	public User validateUser(User user) {
		// TODO Auto-generated method stub
		return ur.verify(user.getUserId(),user.getPassword());
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return ur.save(user);
	}

	@Override
	public User removeUser(User user) {
		// TODO Auto-generated method stub
		ur.delete(user);
		return user;
	}

	@Override
	public User getPassword(String userId) {
		return ur.getPassword(userId);
	}

	/*@Override
	public User signOut(User user) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
