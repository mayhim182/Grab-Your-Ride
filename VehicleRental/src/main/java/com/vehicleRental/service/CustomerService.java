package com.vehicleRental.service;

import java.util.List;

import com.vehicleRental.model.Customer;

public interface CustomerService {
	public Customer addCustomer(Customer customer);
	public Customer removeCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer viewCustomer(Customer customer);
	public List<Customer> viewAllCustomers(String vehicleType);
	public List<Customer> viewAllCustomersByLocation(String location);
	public Customer viewCustomerByEmail(String email);
	public List<Customer> viewAllCustomer();
}
