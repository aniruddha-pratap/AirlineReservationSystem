package com.sjsu.airline.Passengers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sjsu.airline.repositories.PassengerRepository;

@Service
public class PassengerService {

	@Autowired
	private PassengerRepository passengerRepository;
	
	public Passenger getPassenger(int id){
		return passengerRepository.findOne(id);
	}
	
	public List<Passenger> getAllPassengers(){
		return passengerRepository.findAll();
	}
	
	public void createPassenger(Passenger passenger){
		passengerRepository.save(passenger);
	}
	
	public void deletePassenger(int id){
		passengerRepository.delete(id);
	}
	
	public void updatePassenger(int id, Passenger passenger){
		passengerRepository.save(passenger);
	}
	
}
