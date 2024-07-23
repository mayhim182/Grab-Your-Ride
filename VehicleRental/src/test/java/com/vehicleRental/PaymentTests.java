package com.vehicleRental;



	import static org.junit.jupiter.api.Assertions.assertEquals;
	import static org.mockito.Mockito.verify;
	import static org.mockito.Mockito.when;
	import java.time.LocalDate;
	import java.util.List;
	import java.util.stream.Collectors;
	import java.util.stream.Stream;

	import javax.persistence.CascadeType;
	import javax.persistence.OneToOne;

	import org.junit.jupiter.api.Test;
	import org.junit.runner.RunWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.boot.test.mock.mockito.MockBean;
	import org.springframework.test.context.junit4.SpringRunner;

	import com.vehicleRental.model.Booking;
	import com.vehicleRental.model.Customer;
	import com.vehicleRental.model.Driver;
	import com.vehicleRental.model.Payment;
	import com.vehicleRental.model.Vehicle;
	import com.vehicleRental.repository.PaymentRepository;
	import com.vehicleRental.service.PaymentService;
	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class PaymentTests {
		@Autowired
		private PaymentService paymentService;
		@MockBean
		private PaymentRepository repository;
		@Test
		public void addPayment() {
			Payment payment=new Payment(45,"UPI",LocalDate.parse("2022-11-23"),new Booking(),"Successfull");
			when(repository.save(payment)).thenReturn(payment);
			assertEquals( payment,paymentService.addPayment(payment));
		}
		@Test
		public void CalculateMonthlyRevenue()
		{
			when(repository.getAllPaymentbetweenDates(LocalDate.parse("2022-10-01"),LocalDate.parse("2023-01-01"))).thenReturn(
					Stream.of(new Payment(45,"UPI",LocalDate.parse("2022-11-23"),new Booking(12,new Customer(),new Vehicle(),LocalDate.parse("2022-11-23"),LocalDate.parse("2022-11-26"),"official",20000,200,"Pending"),"Successful"),
							new Payment(46,"Debit Card",LocalDate.parse("2022-11-24"),new Booking(13,new Customer(),new Vehicle(),LocalDate.parse("2022-12-01"),LocalDate.parse("2022-12-03"),"official",35000,150,"Pending"),"Successful"),
							new Payment(47,"Credit Card",LocalDate.parse("2022-11-25"),new Booking(14,new Customer(),new Vehicle(),LocalDate.parse("2022-12-04"),LocalDate.parse("2022-12-06"),"official",35000,300,"Pending"),"Successful")).toList());
			assertEquals(90000.0,paymentService.CalculateMonthlyRevenue(LocalDate.parse("2022-10-01"),LocalDate.parse("2023-01-01")));
		}
		@Test
		public void viewAllPayment(){
			List<Payment>paymentList=repository.findAll();
			assertEquals(paymentList,paymentService.viewAllPayment());
		}
	}