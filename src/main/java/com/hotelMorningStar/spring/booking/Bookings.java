package com.hotelMorningStar.spring.booking;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "Bookings" )
public class Bookings {
	@Id
	@JsonProperty
	private String id;
	@JsonProperty
	private String uId;
	@JsonProperty
	private String rId;
	@JsonProperty
	private int bookedDays;
	@JsonProperty
	private String bookedFrom;
	@JsonProperty
	private String bookedTo;
	@JsonProperty
	private int costPerDay;
	@JsonProperty
	private boolean currentStatus;
	
	public boolean isCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(boolean currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public int getBookedDays() {
		return bookedDays;
	}
	public void setBookedDays(int bookedDays) {
		this.bookedDays = bookedDays;
	}
	public String getBookedFrom() {
		return bookedFrom;
	}
	public void setBookedFrom(String bookedFrom) {
		this.bookedFrom = bookedFrom;
	}
	public String getBookedTo() {
		return bookedTo;
	}
	public void setBookedTo(String bookedTo) {
		this.bookedTo = bookedTo;
	}
	public int getCostPerDay() {
		return costPerDay;
	}
	public void setCostPerDay(int costPerDay) {
		this.costPerDay = costPerDay;
	}
	
}
