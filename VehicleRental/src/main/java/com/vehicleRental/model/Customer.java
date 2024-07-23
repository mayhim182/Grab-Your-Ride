package com.vehicleRental.model;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="customer_table")
public class Customer {
	@Id
	@GeneratedValue
private int customerId;
private String firstName;
private String lastName;
private String emailId;
private String mobileNumber;
private String address;


public Customer(String firstName, String lastName, String emailId, String mobileNumber, String address) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.emailId = emailId;
	this.mobileNumber = mobileNumber;
	this.address = address;
}

public Customer(int customerId, String firstName, String lastName, String emailId, String mobileNumber,
		String address) {
	super();
	this.customerId = customerId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.emailId = emailId;
	this.mobileNumber = mobileNumber;
	this.address = address;
}
public Customer() {}
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
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
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}


}
