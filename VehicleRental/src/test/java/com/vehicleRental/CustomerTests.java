package com.vehicleRental;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicleRental.model.Customer;
import com.vehicleRental.repository.CustomerRepository;
import com.vehicleRental.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTests {
	
	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;

	@Test
	public void addCustomersTest() {
		Customer customer=new Customer(234, "siva", "ram", "siv@gmail.com", "1234567891", "pune");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.addCustomer(customer));
		
	}
	
	@Test
	public void updateCustomerTest() {
		Customer customer=new Customer(234, "siva", "singh", "siv@gmail.com", "1234567891", "bihar");
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customer, customerService.addCustomer(customer));
	}

}
