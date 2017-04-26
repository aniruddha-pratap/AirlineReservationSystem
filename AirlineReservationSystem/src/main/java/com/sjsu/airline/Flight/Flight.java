package com.sjsu.airline.Flight;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sjsu.airline.Passengers.Passenger;
import com.sjsu.airline.Plane.Plane;
import com.sjsu.airline.Reservations.Reservation;


@Entity
@Table(name="flight")
public class Flight {
	
	@Id
	@GeneratedValue
	private int number;
	private int price;
	@Column(name="source")
	private String from;
	@Column(name="destination")
	private String to;
	private Date departureTime;
	private Date arrivalTime;
	private int seatsLeft;
	private String description;
	
	
	@ManyToOne(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="order_number")
	@JsonBackReference
	private Reservation reservation;
	
	//@Embedded
	//private Plane plane;
	
	/*public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane plane) {
		this.plane = plane;
	}*/
		
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Date getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	public Date getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getSeatsLeft() {
		return seatsLeft;
	}
	public void setSeatsLeft(int seatsLeft) {
		this.seatsLeft = seatsLeft;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
