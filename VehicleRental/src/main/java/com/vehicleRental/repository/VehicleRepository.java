package com.vehicleRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vehicleRental.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{
	 
	@Query(value="select v from Vehicle v where type=?1")
	public Vehicle getVehicleByType(String type);
	
	@Query(value="select v from Vehicle v where v.vehicleNumber=?1")
	public Vehicle checkIfVehicleExists(String vehicleNumber);
}
