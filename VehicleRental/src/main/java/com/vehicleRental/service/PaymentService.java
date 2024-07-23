package com.vehicleRental.service;

import java.time.LocalDate;
import java.util.List;

import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Payment;
import com.vehicleRental.model.Vehicle;

public interface PaymentService {
public Payment addPayment(Payment payment);
public Payment cancelPayment(Payment payment);
public Payment viewPayment(Booking booking);
public List<Payment> viewAllPayment(Vehicle vehicle);
public double CalculateMonthlyRevenue(LocalDate d1,LocalDate d2);
public double calculateTotalPayment(Vehicle vehicle);
public List<Payment> viewAllPayment();
public int updatePaymentStatus(Payment payment,String status);
}
