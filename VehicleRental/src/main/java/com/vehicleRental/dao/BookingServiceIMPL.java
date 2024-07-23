package com.vehicleRental.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Customer;
import com.vehicleRental.model.Vehicle;
import com.vehicleRental.repository.BookingRepository;
import com.vehicleRental.service.BookingService;
@Service
public class BookingServiceIMPL implements BookingService {
	@Autowired
	private BookingRepository br;
	
	//test
	@Override
	public Booking addBooking(Booking b) {
		Booking booking = br.save(b);
		return booking;
	}

	//test
	@Override
	public int updateBooking(Booking b) {
		// TODO Auto-generated method stub
		System.out.println(b.getBookingId()+" "+b.getTotalCost()+" "+b.getBookedTillDate()+" "+b.getDistance());
		return br.updateBooking(b.getBookingDate(),b.getBookedTillDate(),b.getDistance(),b.getTotalCost(), b.getBookingId());
	}

	@Override
	public Booking cancelBooking(Booking b) {
	    br.delete(b);
	    return b;
		
	}
	//test
	@Override
	public Booking viewBooking(int bookingId) {
		Booking b= br.findById(bookingId).get();
		return b;
	}

	@Override
	public List<Booking> viewAllBookings(String type) {
		return br.viewAllByVehicle(type);
	}

	@Override
	public List<Booking> viewAllBooking(String email) {
		return br.viewAllByCustomer(email);
	}

	@Override
	public List<Booking> viewAllBookingByDate(LocalDate bdate) {
		return br.viewByDate(bdate);
	}

	@Override
	public int updateApprove(Booking booking,String status) {
		return br.approveStatus(status, booking.getBookingId());
	}


	@Override
	public List<Booking> viewAll() {
		List<Booking> list = br.findAll();
		List<Booking> pendingList = new ArrayList<>();
		List<Booking> finalList = new ArrayList<>();
		List<Booking> nonpendingList = new ArrayList<>();
		for(Booking b : list) {
			if(b.getBookingStatus()!=null) {
				if(b.getBookingStatus().equals("Pending")) {
					pendingList.add(b);
				}
				else {
					nonpendingList.add(b);
				}
			}
		}
		finalList.addAll(pendingList);
		finalList.addAll(nonpendingList);
		return finalList;
	}
	

}
