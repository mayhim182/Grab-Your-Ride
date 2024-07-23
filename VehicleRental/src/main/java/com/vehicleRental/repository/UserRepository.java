package com.vehicleRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vehicleRental.model.User;

public interface UserRepository extends JpaRepository< User, String>  {
	@Query (value = "select u from User u where u.userId=?1 and u.password=?2")
	public User verify(String userId, String password);
	public User findByUserId(String userId);
	
	@Query(value="select u from User u where u.userId=?1")
	public User getPassword(String userId);
}
