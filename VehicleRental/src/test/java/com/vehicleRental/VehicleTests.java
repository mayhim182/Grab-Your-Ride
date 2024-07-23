package com.vehicleRental;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vehicleRental.model.Driver;
import com.vehicleRental.model.Vehicle;
import com.vehicleRental.repository.VehicleRepository;
import com.vehicleRental.service.vehicleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleTests {
	@Autowired
private vehicleService vehicleService;
@MockBean
private VehicleRepository vehicleRepository;
@Test
public void getVehicleByTypeTest() {
	Vehicle v=new Vehicle(100,"TestCar",new Driver(100,"TestDriverFName","TestDriverLName","TestAddress","9876543210","TestMail","TestLiscence"),"Volvo","TestCategory","TestDesc","TestLoc",0,00,2900);
	when(vehicleRepository.getVehicleByType("Volvo")).thenReturn(v);
	assertEquals(v, vehicleService.getVehicle("Volvo"));
}
@Test
public void checkIfVehicleExistTest() {
	Vehicle v=new Vehicle(100,"TestCar",new Driver(100,"TestDriverFName","TestDriverLName","TestAddress","9876543210","TestMail","TestLiscence"),"Volvo","TestCategory","TestDesc","TestLoc",0,00,2900);
when(vehicleRepository.checkIfVehicleExists("TestCar")).thenReturn(v);
assertEquals(v,vehicleRepository.checkIfVehicleExists("TestCar"));
}


}