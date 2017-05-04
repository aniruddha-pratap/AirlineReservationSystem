package com.sjsu.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sjsu.airline.Flight.Flight;

public interface FlightRepository extends JpaRepository<Flight, String>{

}
