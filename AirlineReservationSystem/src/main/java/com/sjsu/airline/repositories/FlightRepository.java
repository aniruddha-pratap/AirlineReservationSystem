package com.sjsu.airline.repositories;


import com.sjsu.airline.Flight.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FlightRepository extends JpaRepository<Flight,String>{

}
