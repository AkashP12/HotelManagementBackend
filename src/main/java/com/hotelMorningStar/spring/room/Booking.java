package com.hotelMorningStar.spring.room;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking {
	@JsonProperty
	Boolean bookingStatus;
	
	@JsonProperty
	String bookingFrom;
	
	@JsonProperty
	String bookingTo;

	@JsonProperty
	String uId;
	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public Boolean getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(Boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getBookingFrom() {
		return bookingFrom;
	}

	public void setBookingFrom(String bookingFrom) {
		this.bookingFrom = bookingFrom;
	}

	public String getBookingTo() {
		return bookingTo;
	}

	public void setBookingTo(String bookingTo) {
		this.bookingTo = bookingTo;
	}
}
