package com.vehicleRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Customer;
import com.vehicleRental.model.Vehicle;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
//	public Customer findCustomerById(Customer customer);
	@Query(value="Select b from Booking b where b.vehicle.type=?1")
	public List<Booking> viewBookingByVehicleType(String vehicleType);
//	@Query(value="Select v from Vehicle v where v.location=?1")
//	public List<Vehicle> viewCustomerByLocation(String location);
	@Query(value="Select b from Booking b where b.vehicle.location=?1")
	public List<Booking> viewBookingByLocation(String location);
	@Query(value="select c from Customer c where c.emailId=?1")
	public Customer viewCustomerByEmail(String emailId);
	
	
}
