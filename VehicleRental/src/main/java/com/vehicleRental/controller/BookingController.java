package com.vehicleRental.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Deque;
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

import com.vehicleRental.exception.InvalidBookingDetailsException;
import com.vehicleRental.exception.NoBookingsException;
import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Customer;
import com.vehicleRental.model.Vehicle;
import com.vehicleRental.repository.BookingRepository;
import com.vehicleRental.service.BookingService;
import com.vehicleRental.service.EmailService;

import antlr.collections.Stack;

@RestController
@CrossOrigin("*")
public class BookingController {
	Logger logger = LoggerFactory.getLogger(BookingController.class);
	@Autowired
	private EmailService emailService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private BookingRepository br;
	
//	To Approve/Deny the booking status
	@PutMapping(path="update/approve/{status}")
	public int updateApprove(@PathVariable String status,@RequestBody Booking booking) {
		try {
			emailService.sendSimpleMail(booking.getCustomer().getEmailId(), "Your Booking Id: "+booking.getBookingId()+" status is updated to "+status, "Booking Status Update");
			logger.error(booking.getBookingId()+"Updation Mail Sent");
		}
		catch(Exception e ){
			logger.error("Updation Mail Failure");
		}
		logger.info("Booking Status Changed To"+status);
		return bookingService.updateApprove(booking,status);
	}
	
//	To Add a booking
	@PostMapping(path="add/booking")
	public Booking addBooking(@RequestBody Booking b) throws InvalidBookingDetailsException {
//		System.out.println(b.getCustomer().getEmailId());
		logger.info("Booking Added");
		if(b.getDistance()<=0 || b.getBookingDate().isAfter(b.getBookedTillDate())) {
			logger.error("Invalid Booking Details");
			throw new InvalidBookingDetailsException("Invalid Booking Details");
		}
		try {
            emailService.sendSimpleMail(b.getCustomer().getEmailId(), "Thank You for your booking request.Please proceed to payment.\nYour booking is subject to admin's approval and you can check your booking status on the website!", "Booking Request Recieved");
            emailService.sendSimpleMail("grabyourrideg5@gmail.com", "A new booking request is recieved for our app"+ " cost:" +b.getTotalCost(), "New Booking Request");
            return bookingService.addBooking(b);
            }
         catch (Exception e) {
                System.out.println(e);
                return bookingService.addBooking(b);
            }
	}
	
//	To Update Booking using booking Id
	@PutMapping(path="update/booking/{bookingId}")
	public Booking updateBookingId(@PathVariable String bookingId, @RequestBody Booking booking) {
		logger.info("Booking Updated by Id");
		return br.save(booking);
	}
	
//	To Update booking using booking object
	@PutMapping(path="update/booking")
	public int updateBooking(@RequestBody Booking b) {
		logger.info("Booking Updated");
		return bookingService.updateBooking(b);
	}
//	To delete Booking
	@DeleteMapping(path="delete/booking")
	public Booking deleteBooking(@RequestBody Booking b) {
		bookingService.cancelBooking(b);
		return b;
	}
//	To view booking by id
	@GetMapping(path="view/booking/{id}")
	public Booking viewById(@PathVariable int id) {
		logger.info("Booking Viewed by Id");
		return bookingService.viewBooking(id);
	}
//	To view List of Bookings by vehicle type
	@GetMapping(path="viewByVehicle/booking/{type}")
	public List<Booking> viewByVehicle(@PathVariable String type){
		logger.info("Booking Viewed by Vehicle");
		return bookingService.viewAllBookings(type);
	}
//	To view Bookings By Customer
	@GetMapping(path="viewByCustomer/booking/{email}")
	public List<Booking> viewByCustomer(@PathVariable String email) throws NoBookingsException{
		if(bookingService.viewAllBooking(email).isEmpty()){
			logger.error("No Bookings By Customer Error");
			throw new NoBookingsException(email);
		}
		logger.info("Booking Viewed by Customer");
		return bookingService.viewAllBooking(email);
	}
//	To View Bookings By Date
	@GetMapping(path="viewByDate/booking/{date}")
	public List<Booking> viewByDate(@PathVariable String date){
		logger.info("Booking Viewed by Date");
		LocalDate ld = LocalDate.parse(date);
		System.out.print(ld);
		return bookingService.viewAllBookingByDate(ld);
	}
	
//	To Check if vehicle is available on specified dates
	@GetMapping(path="booking/checkAvailability/{type}/{bookingDate}/{bookingTillDate}")
	public int checkAvailability(@PathVariable String type,@PathVariable String bookingDate,@PathVariable String bookingTillDate){
		System.out.println(type +""+bookingDate+""+bookingTillDate);
		List<Booking> list = br.checkVehicleAvailability(type, LocalDate.parse(bookingDate),LocalDate.parse(bookingTillDate),"Denied");
		if(list.isEmpty()) {
			logger.info("Vehicle Available");
			return 1;
		}
		else {
			logger.info("Vehicle Unavailable");
			return 0;
		}
		
	}
//	View All Bookings
	@GetMapping(path="view/allBookings")
	public List<Booking> allBookings(){
		return bookingService.viewAll();
	}
	
}
