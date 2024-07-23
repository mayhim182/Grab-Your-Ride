package com.vehicleRental.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Customer;
import com.vehicleRental.repository.BookingRepository;
import com.vehicleRental.repository.CustomerRepository;
import com.vehicleRental.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository cpr1;
	@Autowired
	BookingRepository bpr1;
	
	
	//test
	@Override
	public Customer addCustomer(Customer customer) {
		return cpr1.save(customer);	
	}

	@Override
	public Customer removeCustomer(Customer customer) {
		cpr1.delete(customer);
		return customer;
	}
	//test
	@Override
	public Customer updateCustomer(Customer customer) {
		//Assuming some date is changed
		cpr1.save(customer);
		return customer;
	}
	//test
	@Override
	public Customer viewCustomer(Customer customer){
		return cpr1.findById(customer.getCustomerId()).get();
	}
	//test
	@Override
	public List<Customer> viewAllCustomers(String vehicleType) {
		List<Customer> listCustomer=new ArrayList<Customer>();
		for(Booking b:cpr1.viewBookingByVehicleType(vehicleType)) {
			listCustomer.add(b.getCustomer());
		}
		return listCustomer;
	}

	@Override
	public List<Customer> viewAllCustomersByLocation(String location) {
		List<Customer> listCustomer=new ArrayList<Customer>();
		for(Booking b:cpr1.viewBookingByLocation(location)) {
			listCustomer.add(b.getCustomer());
		}
		return listCustomer;
	}
	//test
	@Override
	public Customer viewCustomerByEmail(String email) {
		return cpr1.viewCustomerByEmail(email);
	}

	@Override
	public List<Customer> viewAllCustomer() {
		return cpr1.findAll();
	}
	
	

}
