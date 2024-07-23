package com.vehicleRental.controller;

import java.time.LocalDate;
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

import com.vehicleRental.model.Booking;
import com.vehicleRental.model.Payment;
import com.vehicleRental.model.Vehicle;
import com.vehicleRental.service.PaymentService;

@CrossOrigin("*")
@RestController
public class paymentController {
		Logger logger = LoggerFactory.getLogger(paymentController.class);
	  @Autowired
	  private PaymentService service;
	  
//	  To add Payment object in the db
	  @PostMapping(path="add/payment")
	  public Payment addPayment(@RequestBody Payment paymentObj)
	  {
		  logger.info("Payment Processed");
		  return service.addPayment(paymentObj);
	  }
	  
//	  To View all payments
	  @GetMapping(path="view/allpayment")
	  public List<Payment> viewAllPayment(@RequestBody Vehicle vehicle)
	  {		logger.info("Payment List Viewed");
		  return service.viewAllPayment(vehicle);
	  }
	  
//	  To view payment by booking object
	  @GetMapping(path="view/payment")
	  public Payment viewPayment(@RequestBody Booking booking)
	  {		logger.info("Payment Viewed By Booking");
		  return service.viewPayment(booking);
	  }
	  
//	  To delete payment
	  @DeleteMapping(path="cancel/payment")
	  public Payment cancelPayment(@RequestBody Payment payment)
	  {		
		  return service.cancelPayment(payment);
	  }
	  
//	  To calculate monthly revenue on the basis of dates.
	  @GetMapping(path="calculate/revenue/{ld1}/{ld2}")
	  public double CalculateMonthlyRevenue(@PathVariable String ld1,@PathVariable String ld2)
	  {		logger.info("Monthly Revenue Calculated");
		  LocalDate d1=LocalDate.parse(ld1);
		  LocalDate d2=LocalDate.parse(ld2);
		  return service.CalculateMonthlyRevenue(d1, d2);
	  }
	  
//	  To calculate total payment
	  @GetMapping(path="calculate/totalPayment")
	  public double calculateTotalPayment(@RequestBody Vehicle vehicle)
	  {		logger.info("Total Revenue Calculated");
		  return service.calculateTotalPayment(vehicle);
	  }
	  
//	  To get list of  all payments
	  @GetMapping(path="/payment/viewAllPayments")
		public List<Payment> viewAllPayments(){
		  logger.info("All Payments Viewed");
			return service.viewAllPayment();
		}
	  
//	  To Update Payment Status
	  @PutMapping(path="update/payment/{status}")
      public int updateApprove(@PathVariable String status,@RequestBody Payment payment) {
		  logger.info("Payment Status Changed");
            return service.updatePaymentStatus(payment,status);
        }
	   
}
