package com.vehicleRental.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicleRental.model.Vehicle;
import com.vehicleRental.repository.VehicleRepository;
import com.vehicleRental.service.vehicleService;
@Service
public class vehicleServiceImpl implements vehicleService {
	@Autowired
	private VehicleRepository vr;
	@Override
	public Vehicle addVehicle(Vehicle v) {
		return vr.save(v);
	}

	@Override
	public Vehicle getVehicle(String type) {
		return vr.getVehicleByType(type);
		}
}
