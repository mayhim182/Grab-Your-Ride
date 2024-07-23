package com.vehicleRental.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Customer;
import com.vehicleRental.model.Vehicle;

public interface BookingService {
	public Booking addBooking(Booking b);
	public int updateBooking(Booking b);
	public Booking cancelBooking(Booking b);
	public Booking viewBooking(int bookingId);
	public List<Booking> viewAllBookings(String type);
	public List<Booking> viewAllBooking(String email);
	public List<Booking> viewAllBookingByDate(LocalDate bdate);
	public int updateApprove(Booking booking,String status);
	public List<Booking> viewAll();

}
