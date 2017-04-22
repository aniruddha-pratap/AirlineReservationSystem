package com.sjsu.airline.repositories;

import com.sjsu.airline.Passengers.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{

}
