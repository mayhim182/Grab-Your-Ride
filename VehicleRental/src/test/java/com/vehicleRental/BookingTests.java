package com.vehicleRental;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MvcResult;

import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Customer;
import com.vehicleRental.model.Vehicle;
import com.vehicleRental.repository.BookingRepository;
import com.vehicleRental.service.BookingService;

@SpringBootTest
class BookingTests {

	
	@Autowired
	private BookingService bookingService;
	
	@MockBean
	private BookingRepository bookingRepository;
	

	@Test
	public void getBookingsTest() {
		when(bookingRepository.findAll()).thenReturn(Stream.of(
				new Booking(101,new Customer(),new Vehicle(),LocalDate.parse("2022-01-01"),LocalDate.parse("2022-01-05"),"Leisure",4000,200,"Approved"),
				new Booking(102,new Customer(),new Vehicle(),LocalDate.parse("2022-02-01"),LocalDate.parse("2022-01-06"),"Leisure",5000,240,"Denied"),
				new Booking(103,new Customer(),new Vehicle(),LocalDate.parse("2022-03-01"),LocalDate.parse("2022-01-07"),"Leisure",6000,250,"Approved")).collect(Collectors.toList()));
				assertEquals(3, bookingService.viewAll().size());
	}
	
	@Test
	public void getBookingByDate() {
		LocalDate date = LocalDate.parse("2022-01-01");
		when(bookingRepository.viewByDate(date)).thenReturn(Stream.of(
				new Booking(101,new Customer(),new Vehicle(),LocalDate.parse("2022-01-01"),LocalDate.parse("2022-01-05"),"Leisure",4000,200,"Approved")).collect(Collectors.toList()));
		assertEquals(1, bookingService.viewAllBookingByDate(date).size());
	}
	
	@Test
	public void updateBooking() {
		when(bookingRepository.updateBooking(LocalDate.parse("2022-01-04"),LocalDate.parse("2022-01-09"),150,2000,101)).thenReturn(1);
		assertEquals(1, bookingService.updateBooking(new Booking(101,new Customer(),new Vehicle(),LocalDate.parse("2022-01-04"),LocalDate.parse("2022-01-09"),"Leisure",2000,150,"Approved")));
	}
	
	
	
//	@Test
//	public void viewBookingByType() {
//		
//	}
//	public void update() {
//		MvcResult result =this.mockMvc.perform(get("view/allBookings")).andExpect(1).andReturn();;
//	}
	
	
	

}
