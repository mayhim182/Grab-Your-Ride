package com.vehicleRental.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vehicleRental.model.*;
import com.vehicleRental.repository.VehicleRepository;
import com.vehicleRental.service.vehicleService;

import antlr.collections.List;

@RestController
@CrossOrigin("*")
public class VehicleController {
	@Autowired
	private vehicleService vehicleService;
	@Autowired
	private VehicleRepository vr;

	Logger logger = LoggerFactory.getLogger(VehicleController.class);
	
//	Adding vehicle object
	@PostMapping(path="/add/vehicle")
	public Vehicle addVehicle(@RequestBody Vehicle v) {
		logger.info("Vehicle Viewed");
		return vehicleService.addVehicle(v);
	}
//	To get vehicle on the basis of types
	@GetMapping(path="/view/vehicle/{type}")
	public Vehicle getVehicle(@PathVariable String type) {
		logger.info("Vehicle Viewed By Type");
		return vehicleService.getVehicle(type);
	}
//	To check if vehicle exists on the basis of vehicle number
	@GetMapping(path="/check/vehicle/{vehicleNumber}")
	public int checkIfVehicleExists(@PathVariable String vehicleNumber) {
		Vehicle v = vr.checkIfVehicleExists(vehicleNumber);
		if(v==null) {
			logger.info("Vehicle Exists ,Proceed to Booking!");
			return 1;
		}
		else {
			logger.info("Vehicle Booked for the given dates!");
			return 0;
		}
	}
	
}
