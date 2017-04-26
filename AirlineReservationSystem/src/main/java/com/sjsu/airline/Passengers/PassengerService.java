package com.sjsu.airline.Passengers;

import java.util.List;
import java.util.UUID;
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
	
	public Passenger createPassenger(Passenger pass){
		try{
			passengerRepository.save(pass);
		}catch(Exception e){
			pass = null;
		}
		return pass;
	}
	
	public void deletePassenger(int id){
		passengerRepository.delete(id);
	}
	
	public Passenger updatePassenger(int id, String firstName, String lastName, int age, String gender, String phone){
		System.out.println("before update");
		Passenger p=passengerRepository.findOne(id);
		System.out.println("Processed update");
		if(p !=null){
			p.setFirstname(firstName);
			p.setLastname(lastName);
			p.setGender(gender);
			p.setAge(age);
			p.setPhone(phone);
			passengerRepository.save(p);
		}
		return passengerRepository.findOne(id);
	}
	
}
