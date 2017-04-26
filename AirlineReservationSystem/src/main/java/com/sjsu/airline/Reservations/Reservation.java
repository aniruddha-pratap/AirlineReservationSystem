package com.sjsu.airline.Reservations;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sjsu.airline.Flight.Flight;
//import com.sjsu.airline.Flight.Flight;
import com.sjsu.airline.Passengers.Passenger;

@Entity
@Table(name = "reservation")
public class Reservation {
	
	@Id
	@GeneratedValue
	@Column(name="order_number")
	private int orderNumber;
	private int price;
	
	@ManyToOne(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="passenger_id")
	@JsonBackReference
	private Passenger passenger; 
	
	@OneToMany(mappedBy="reservation", fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Flight> flight;
	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
		
}
