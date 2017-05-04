package com.sjsu.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sjsu.airline.Reservations.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

//    List<Reservation> findByPassengerId(int passengerID);
//    List<Reservation> findByFrom(String from);
//
//    List<Reservation> fingByTo(String to);
//
//    List<Reservation> findByFlightNumber(String flightNumber);

}
