package com.vehicleRental.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Payment;
import com.vehicleRental.model.Vehicle;
import com.vehicleRental.repository.PaymentRepository;
import com.vehicleRental.service.PaymentService;
@Service
public class PaymentImpl implements PaymentService {
	@Autowired
	private PaymentRepository repository;
	
	@Override
	public Payment addPayment(Payment payment) {
		// TODO Auto-generated method stub
		return repository.save(payment);
	}

	@Override
	public Payment cancelPayment(Payment payment) {
		// TODO Auto-generated method stub
		repository.delete(payment);
		if(repository.existsById(payment.getPaymentId()))
	       {
			System.out.println("unable to delete!!!");
			return payment;
	       }
		System.out.println("Deleted Successfully");
	       return payment;
	}
		
	

	@Override
	public Payment viewPayment(Booking booking) {
		// TODO Auto-generated method stub
		 Payment payment=repository.viewPayment(booking.getBookingId());
		 return payment;
//		for(Payment p : paymentList) {
//			if(p.getBooking().getBookingId()==booking.getBookingId()) {
//				return p;
//			}
//		}
//		return null;
	}

	@Override
	public List<Payment> viewAllPayment(Vehicle vehicle) {
		// TODO Auto-generated method stub
		List<Payment>paymentList=repository.viewAllPayment(vehicle.getVehicleId());
		
		return paymentList;
	}

	@Override
	public double CalculateMonthlyRevenue(LocalDate d1, LocalDate d2) {
		// TODO Auto-generated method stub
		List<Payment> paymentList=repository.getAllPaymentbetweenDates(d1, d2);
		double revenue=0;
		for(Payment p : paymentList)
		{if(p.getPaymentStatus().equalsIgnoreCase("Successful")) {
			revenue+=p.getBooking().getTotalCost();
		}
		}
		return revenue;
	}

	@Override
	public double calculateTotalPayment(Vehicle vehicle) {
		// TODO Auto-generated method stub
		List<Payment>paymentList=repository.viewAllPayment(vehicle.getVehicleId());
		double sum=0;
		for(Payment p : paymentList)
		{
			sum+=p.getBooking().getTotalCost();
		}
		return sum;
		}
	
	@Override
	public List<Payment> viewAllPayment() {
		return repository.findAll();
	}

	@Override
	public int updatePaymentStatus(Payment payment, String status) {
		return repository.updatePaymentStatus(status, payment.getPaymentId());
	}
}
