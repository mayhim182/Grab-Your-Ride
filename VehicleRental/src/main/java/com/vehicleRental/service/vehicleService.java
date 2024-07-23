package com.vehicleRental.service;

import com.vehicleRental.model.Vehicle;

public interface vehicleService {
	public Vehicle addVehicle(Vehicle v);
	public Vehicle getVehicle(String type);

}
