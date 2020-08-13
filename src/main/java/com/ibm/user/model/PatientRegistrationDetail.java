package com.infy.user.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonFormat;


public class PatientRegistrationDetail {
	@NotNull(message="First Name must not be null")
	private String firstName;
	private String lastName;
	@NotNull(message="Password must not be null")
	private String password;
	@NotNull(message="Date Of Birth Name must not be null")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd",timezone="IST")
	private Date dateOfBirth;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd",timezone="IST")
	private Date dateOfRegistration;
	@Indexed(unique =true)
	@NotNull(message="Email Address must not be null")
	private String email;
	private Long mobileNo;
	@NotNull(message="AAdhar Number must not be null")
	@Indexed(unique= true)
	private String  aadhar;
	private Address address;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}			
}
