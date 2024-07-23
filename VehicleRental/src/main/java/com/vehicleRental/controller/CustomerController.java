package com.vehicleRental.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vehicleRental.dao.CustomerServiceImpl;
import com.vehicleRental.model.Customer;
import com.vehicleRental.service.EmailService;

@RestController
@CrossOrigin("*")
public class CustomerController {
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private EmailService emailService;
	@Autowired
	CustomerServiceImpl csi;
//	test
//	To Add a customer object
	@PostMapping("/addCustomer") 
	public Customer addCustomer(@RequestBody Customer customer) {
		
		csi.addCustomer(customer);
		logger.info(customer.getFirstName()+" Customer Viewed");
		try {
            String body="Congratulations "+customer.getFirstName()+" You are now a customer of GrabYourRide book your ride now";
            emailService.sendSimpleMail(customer.getEmailId(), body, "New Customer Login");
            logger.info(customer.getFirstName()+" Mail Sent");
            }
            catch (Exception e) {
                System.out.println(e);
                // TODO: handle exception
            }
		return customer;
	}
//	To delete a customer
	@DeleteMapping("/deleteCustomer")
	public Customer deleteCustomer(@RequestBody Customer customer) {
		logger.info(" Customer Deleted");
		csi.removeCustomer(customer);
		return customer;
	}
//	test
//	To Update Customer 
	@PutMapping("/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		logger.info(" Customer Updated");
		//Assuming some part has been updated
		return csi.updateCustomer(customer);
	}
//	To View Customer 
	@GetMapping("/viewCustomer")
	public Customer viewCustomer(@RequestBody Customer customer) {
		logger.info(" Customer Viewed");
		csi.viewCustomer(customer);
		return customer;
	}
	
//	To View All Customers
	
	@GetMapping(path="/viewAll/Customer")
	public List<Customer> viewAllCustomer(){
		logger.info(" Customer List Viewed");
		return csi.viewAllCustomer();
	}
	
//	To View Customers by location
	@GetMapping("/viewAll/{location}")
	public List<Customer> viewCustomersByLoc(@PathVariable String location) {
		logger.info(" Customer List Viewed By Location");
		return csi.viewAllCustomersByLocation(location);
	}
//	To View Customers by vehicle type
	@GetMapping("/viewAllT/{vehicleType}")
	public List<Customer> viewCustomersByVT(@PathVariable String vehicleType){
		logger.info(" Customers List Viewed By Vehicle Types");
		return csi.viewAllCustomers(vehicleType);
	}
//	To View Customer by email
	@GetMapping(path="/customer/viewByEmail/{email}")
	public Customer viewCustomerByEmail(@PathVariable String email) {
		return csi.viewCustomerByEmail(email);
	}
	
}
