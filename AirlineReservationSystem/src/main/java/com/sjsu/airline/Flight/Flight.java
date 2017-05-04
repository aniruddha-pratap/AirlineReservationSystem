package com.sjsu.airline.Flight;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sjsu.airline.Passengers.Passenger;
import com.sjsu.airline.Plane.Plane;
import com.sjsu.airline.Reservations.Reservation;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="flight")
public class Flight {

	@Id
	@Column(name="flight_number")
	private String number;
	private int price;
	private String fromSource;
	private String toDestination;
	private Date departureTime;
	private Date arrivalTime;
	private int seatsLeft;
	private String description;

	@OneToOne
	@JoinColumn(name="plane_id")
	@Cascade(value = CascadeType.MERGE)
	@Embedded
	private Plane plane=new Plane();

	@ManyToMany(fetch= FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="order_number")
	@JsonBackReference
	private Set<Reservation> reservation;

	@ManyToMany(fetch= FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
	@JoinColumn(name="passenger_id")
	@JsonBackReference
	private List<Passenger> passengers;

	public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	@Override
	public String toString() {
		return "Flight{" +
				"number='" + number + '\'' +
				", price=" + price +
				", fromSource='" + fromSource + '\'' +
				", toDestination='" + toDestination + '\'' +
				", departureTime=" + departureTime +
				", arrivalTime=" + arrivalTime +
				", seatsLeft=" + seatsLeft +
				", description='" + description + '\'' +
				", plane=" + plane.toString() +
				'}';
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFromSource() {
		return fromSource;
	}
	public void setFromSource(String from) {
		this.fromSource = from;
	}
	public String getToDestination() {
		return toDestination;
	}
	public void setToDestination(String to) {
		this.toDestination = to;
	}

	public List<Passenger> getPassengers(){
		return null;
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

    public void setModel(String model) {
        this.plane.setModel(model);
    }

	public void setCapacity(int capacity){
		this.plane.setCapacity(capacity);
	}
    public void setManufacturer(String manufacturer) {
        this.plane.setManufacturer(manufacturer);
    }
	public void addPassenger(Passenger passenger){
		this.passengers.add(passenger);
	}
    public void setYearOfManufacture(int yearOfManufacture) {
        this.plane.setYearOfManufacture(yearOfManufacture);
    }

	public void setReservation(Reservation reservation) {
		this.reservation.add(reservation);
	}
}