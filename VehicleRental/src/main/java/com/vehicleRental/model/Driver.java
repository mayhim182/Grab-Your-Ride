package com.vehicleRental.model;
import javax.persistence.*;

@Entity
@Table(name="driver_table")
public class Driver {
	@Id
	@GeneratedValue
	private int driverId;
	private String firstName;
	private String lastName;
	private String address;
	private String mobileNumber;
	private String emailId;
	private String licenceNo;
	
	public Driver(int driverId, String firstName, String lastName, String address, String mobileNumber, String emailId,
			String licenceNo) {
		super();
		this.driverId = driverId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.licenceNo = licenceNo;
	}
	public Driver() {}
	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId + ", licenceNo=" + licenceNo
				+ "]";
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getLicenceNo() {
		return licenceNo;
	}
	public void setLicenceNo(String licenceNo) {
		this.licenceNo = licenceNo;
	}
	
}
