package com.hotelMorningStar.spring.room;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "Room" )
public class Room {
	@Id
	@JsonProperty
	private String id;
	@JsonProperty
	private String roomType;
	@JsonProperty
	private int cost;
	@JsonProperty
	private int capacity;
	@JsonProperty
	private List<Booking> bookings;
	
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public List<Booking> getBookings() {
		return bookings;
	}
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	public void setId(String id) {
		this.id = id;
	}
}
