package com.vehicleRental.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vehicleRental.model.*;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	
	@Query(value="select p from Payment p where p.booking.bookingId=?1")
	public Payment viewPayment(int bookingId);
	@Query(value="select p from Payment p where p.booking.vehicle.vehicleId=?1")
	public List<Payment> viewAllPayment(int vehicleId);
	@Query(value="select p from Payment p where p.booking.bookingDate>=?1 and p.booking.bookingDate<=?2")
	public List<Payment> getAllPaymentbetweenDates(LocalDate d1,LocalDate d2);
	@Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update Payment p set p.paymentStatus=?1 where p.paymentId=?2")
    public int updatePaymentStatus(String status,int paymentId);
}
