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
class VehicleRentalApplicationTests {
	
	
//	@Test
//	public void viewBookingByType() {
//		
//	}
//	public void update() {
//		MvcResult result =this.mockMvc.perform(get("view/allBookings")).andExpect(1).andReturn();;
//	}
	
	
	

}
