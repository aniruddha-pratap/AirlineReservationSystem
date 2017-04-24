package com.sjsu.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sjsu.airline.Reservations.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String>{

}
