package com.vehicleRental;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicleRental.model.Customer;
import com.vehicleRental.model.User;
import com.vehicleRental.repository.CustomerRepository;
import com.vehicleRental.repository.UserRepository;
import com.vehicleRental.service.CustomerService;
import com.vehicleRental.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	@Autowired	
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	
	@Test
	public void validateUserTest() {
		User user1=new User();
		user1.setUserId("Ashwini@gmail.com");
		user1.setPassword("123g");
		user1.setRole("user");
		when(repository.verify("Ashwini@gmail.com","123g")).thenReturn(user1);
		assertEquals(user1,service.validateUser(user1));
	}
	
	@Test
	public void addUserTest() {
		User user1=new User();
		user1.setUserId("Ashwini@gmail.com");
		user1.setPassword("123g");
		user1.setRole("user");
		when(repository.save(user1)).thenReturn(user1);
		assertEquals(user1,service.addUser(user1));
	}
	
	@Test
	public void findUserByIdTest() {
		User user1=new User();
		user1.setUserId("Ashwini@gmail.com");
		user1.setPassword("123g");
		user1.setRole("user");
		when(repository.findByUserId(user1.getUserId())).thenReturn(user1);
		assertEquals(user1,repository.findByUserId("Ashwini@gmail.com"));
	}
	
	@Test
	public void removeUserTest() {
		User user1=new User();
		user1.setUserId("Ashwini@gmail.com");
		user1.setPassword("123g");
		user1.setRole("user");
		service.removeUser(user1);
		verify(repository,times(1)).delete(user1);

}
}
