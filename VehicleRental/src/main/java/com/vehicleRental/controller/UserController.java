package com.vehicleRental.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vehicleRental.exception.UserDoesNotExistException;
import com.vehicleRental.exception.UserExistsException;
import com.vehicleRental.model.User;
import com.vehicleRental.repository.UserRepository;
import com.vehicleRental.service.EmailService;
import com.vehicleRental.service.UserService;


@CrossOrigin("*")
@RestController
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
    private EmailService emailService;
	@Autowired
	private UserService ur;
	@Autowired
	private UserRepository userrepo;
	
//	To Check login credentials and implement login
	@PostMapping(path="/login")
	public ResponseEntity<?> login(@RequestBody User userData){
		System.out.println(userData);
		User user=userrepo.findByUserId(userData.getUserId());
		
		if(user.getPassword().equals(userData.getPassword())) {
			  logger.info("User Logged In");
			  
			return ResponseEntity.ok(user);
		}
		logger.info("Wrong Login Credentials");
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
	}
	
//	To get user on the basis of email id
	@GetMapping(path="/getUser/{userId}")
	public Optional<User> getUser(@PathVariable String userId) {
		logger.info("User Queried");
		return userrepo.findById(userId);
	}
	
// Implementation of forget password method
	@GetMapping(path="user/password/{userId}")
	public User getPassword(@PathVariable String userId) throws UserDoesNotExistException {
		logger.info("Password Fetched");
		System.out.println(userId);
		String password= "";

		if(userrepo.findById(userId).isPresent()) {
			password= ur.getPassword(userId).getPassword();
		}
		else {
			throw new UserDoesNotExistException(userId);
		}
		try {
			emailService.sendSimpleMail(userId, "Your Password is:"+password, "GYR:Your Login Password");
			logger.info("Password Emailed");
		}
		catch(Exception e) {
			System.out.println("Error while emailing password");
		}
		return ur.getPassword(userId);
	}
	
//	Delete User
	@DeleteMapping(path ="/remove/User")
	public User removeUser(@RequestBody User user) {
		
		return ur.removeUser(user);
	}
// Validate Using id and password
	@GetMapping(path = "/validate/User")
	public User validateUser(@RequestBody User user) {
		logger.info("User Validated");
		return ur.validateUser(user);
	}
//	Add user object
	@PostMapping(path = "/add/User")
	public User addUser(@RequestBody User user) throws UserExistsException {
//		if(userrepo.findById(user.getUserId())!=null) {
//			throw new E
//		}
		System.out.println("addUserCalled");
		if(userrepo.findById(user.getUserId()).isPresent()) {
			throw new UserExistsException("User Exists");
		}
			System.out.println("addUserCalled");
			ur.addUser(user);
			try {
				 String body="Welcome to the Grab Your Ride.You're now an esteemed customer of Grab Your Ride Family";
			        emailService.sendSimpleMail(user.getUserId(), body, "Welcome User");
			}
			catch(Exception e){
				System.out.println("Error While Sending new user email!");
				return user;
			}
			logger.info(user.getUserId()+" :User Added");
			return user;
	}
}