package com.sjsu.airline.Passengers;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sjsu.airline.Flight.Flight;
import com.sjsu.airline.Reservations.Reservation;

@Entity
@Table(name="passenger")
public class Passenger {

	@Id
	@GeneratedValue
	@Column(name="passenger_id")
	private int id;
	private String firstname;

	private String lastname;
	private int age;
	private String gender;

	@Column(unique=true)
	private String phone;

	@OneToMany(mappedBy="passenger", fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Reservation> reservation;

	/*@ManyToMany(mappedBy="passengers", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Flight> flight;*/


	@ManyToMany(cascade=javax.persistence.CascadeType.ALL)
	@JoinTable(name="flight_passenger", joinColumns= {@JoinColumn(name="passenger_id")},
			inverseJoinColumns = {@JoinColumn(name="flight_number")})
	@JsonManagedReference

    private String lastname;
    private int age;
    private String gender;
    
    @Column(unique=true)
    private String phone;
   
    @OneToMany(mappedBy="passenger", fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation> reservation;

    @ManyToMany(cascade=javax.persistence.CascadeType.ALL)
	@JoinTable(name="flight_passenger", joinColumns= {@JoinColumn(name="passenger_id")}, 
	inverseJoinColumns = {@JoinColumn(name="flight_number")})
    @JsonManagedReference

	private List<Flight> flight;
    
    /*@ManyToOne(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="flight_number")
	@JsonBackReference
	private Flight flight;*/

	public Passenger(){

	}

	public int getPassengerId() {
		return id;
	}
	public void setPassengerId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Reservation> getReservation() {
		return reservation;
	}

/*	public void addReservation(Reservation reservation) {
		this.reservation.add(reservation);
	}*/


//	public void addFlight(Flight flight) {
//		this.flight.add(flight);
//	}
}