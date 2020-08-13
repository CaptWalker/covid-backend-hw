package com.infy.user.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TravelDetail {
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern ="dd-MM-yyyy",timezone="IST")
	private Date dateOfTravel;
	private String fromPlace;
	private String toPlace;
	private Integer stayDurationInDay;
	public Date getDateOfTravel() {
		return dateOfTravel;
	}
	public void setDateOfTravel(Date dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public Integer getStayDurationInDay() {
		return stayDurationInDay;
	}
	public void setStayDurationInDay(Integer stayDurationInDay) {
		this.stayDurationInDay = stayDurationInDay;
	}
	
}
