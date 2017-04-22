package com.sjsu.airline.Passengers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airline")
public class PassangerController {

	@Autowired
	private PassengerService passengerService; 
	
	@GetMapping("/allPassengers")
	public List<Passenger> getAllPassengers(){
		return passengerService.getAllPassengers();
	}
	
	@GetMapping("/passenger/{id}")
	public Passenger getPassenger(@PathVariable String id){
		return passengerService.getPassenger(id);
	}
	
	@PostMapping("/createPassenger")
	public List<Passenger> createPassenger(@RequestBody Passenger passenger){
		passengerService.createPassenger(passenger);
		return passengerService.getAllPassengers();
	}
	
	@PutMapping("/updatePassenger/{id}")
	public List<Passenger> updatePassenger(@RequestBody Passenger passenger, @PathVariable int id){
		passengerService.updatePassenger(id, passenger);
		return passengerService.getAllPassengers();
	}
	
	@DeleteMapping
	public List<Passenger> deletePassenger(@PathVariable String id){
		passengerService.deletePassenger(id);
		return passengerService.getAllPassengers();
	}
}
