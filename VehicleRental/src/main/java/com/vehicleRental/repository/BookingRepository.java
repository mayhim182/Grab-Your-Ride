package com.vehicleRental.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Vehicle;


@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
	@Query(value="select b from Booking b where b.vehicle.type=?1")
	public List<Booking> viewAllByVehicle(String type);
	
	@Query(value="select b from Booking b where b.customer.emailId=?1")
	public List<Booking> viewAllByCustomer(String email);
	
	@Query(value="select b from Booking b where b.bookingDate=?1")
	public List<Booking> viewByDate(LocalDate date);
//																		25               23        24          22        25               23        24          22         25               23        24          22
//	@Query(value="select b from Booking b where b.vehicle.type=?1 and ((b.bookedTillDate>=?3 and b.bookingDate>=?2) or (b.bookedTillDate<=?3 and b.bookingDate>=?2) or (b.bookedTillDate<=?3 and b.bookingDate<=?2)) and b.bookingStatus!=?4")
//	public List<Booking> checkVehicleAvailability(String type,LocalDate date,LocalDate tillDate,String status);
//	                                                                                                                                                                       25              25           24       23
	@Query(value="select b from Booking b where b.vehicle.type=?1 and ((b.bookingDate<=?3 and b.bookingDate>=?2) or (b.bookedTillDate<=?3 and b.bookedTillDate>=?2) or (b.bookedTillDate<=?3 and b.bookingDate>=?2)) and b.bookingStatus!=?4")
	public List<Booking> checkVehicleAvailability(String type,LocalDate date,LocalDate tillDate,String status);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update Booking set bookingStatus=?1 where bookingId=?2")
	public int approveStatus(String bookingStatus,int bookingId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update Booking set bookingDate=?1,bookedTillDate=?2,distance=?3,totalCost=?4 where bookingId=?5")
	public int updateBooking(LocalDate bookingDate,LocalDate bookedTillDate,double distance,double totalCost,int bookingId);

}
