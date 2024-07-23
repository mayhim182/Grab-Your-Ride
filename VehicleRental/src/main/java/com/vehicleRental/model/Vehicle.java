package com.vehicleRental.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="vehicle_table")
public class Vehicle {
	@Id
	@GeneratedValue
	private int vehicleId;
	
	private String vehicleNumber;
	@OneToOne(cascade=CascadeType.ALL)
	private Driver driver;
	private String type;
	private String category;
	private String description;
	private String location;
	private int capacity;
	private double chargesPerKM;
	private double fixedCharges;
	
	public Vehicle(int vehicleId, String vehicleNumber, Driver driver, String type, String category, String description,
			String location, int capacity, double chargesPerKM, double fixedCharges) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleNumber = vehicleNumber;
		this.driver = driver;
		this.type = type;
		this.category = category;
		this.description = description;
		this.location = location;
		this.capacity = capacity;
		this.chargesPerKM = chargesPerKM;
		this.fixedCharges = fixedCharges;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public double getChargesPerKM() {
		return chargesPerKM;
	}
	public void setChargesPerKM(double chargesPerKM) {
		this.chargesPerKM = chargesPerKM;
	}
	public double getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(double fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public Vehicle() {
		super();
	}
}
