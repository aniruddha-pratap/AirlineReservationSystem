package com.sjsu.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sjsu.airline.Reservations.Reservation;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

    List<Reservation> findByPassengerId(int passenger_id);

    @Query(value = "select reservation.* from reservation, flight_passengers, flight where flight_passengers.passengers_passenger_id=ifnull(?1,flight_passengers.passengers_passenger_id) and flight.from_source=ifnull(?2,flight.from_source) and flight.to_destination=ifnull(?3,flight.to_destination) and flight.flight_number=ifnull(?4,flight.flight_number) and reservation.passenger_id=flight_passengers.passengers_passenger_id group by passenger_id", nativeQuery = true)
    List<Reservation> findByPassengerIdOrFlightNumberOrFromSourceOrToDestination(Integer passenger_id,String from, String to,String flightNumber);

    @Query(value="select * from reservation where order_number=?1",nativeQuery = true)
    Reservation getReservation(int reservationId);
}
