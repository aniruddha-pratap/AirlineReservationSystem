package com.sjsu.airline.Passengers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sjsu.airline.Exception.SpecialException;


@RestController
public class PassangerController {

	@Autowired
	private PassengerService passengerService; 
	
	@GetMapping(value="/allPassengers")
	public List<Passenger> getAllPassengers(@RequestParam(value="xml") String xml){
		System.out.println("In get all passengers/..");
		return passengerService.getAllPassengers();
	}
	
	@GetMapping("/passenger/{id}")
	public Passenger getPassenger(@PathVariable int id, @RequestParam(value="json", required=false) String json, @RequestParam(value="xml", required=false) String xml) throws Exception{
		if(passengerService.getPassenger(id) ==  null)
		{
			System.out.println("Inside get if");
			SpecialException e = new SpecialException();
			e.setCode(404);
			e.setMessage("Sorry! The requested passenger with id "+id+" does not exist");
			throw e;
		}
		return passengerService.getPassenger(id);
	}
	
	@PostMapping("/passenger")
	public Passenger createPassenger(@RequestParam(value="firstname", required=true) String firstName, @RequestParam(value="lastname", required=true) String lastName, @RequestParam(value="age", required=true) int age, @RequestParam(value="gender", required=true) String gender, @RequestParam(value="phone", required=true) String phone) throws Exception{
		Passenger pa = new Passenger();
		pa.setFirstname(firstName);
		pa.setLastname(lastName);
		pa.setGender(gender);
		pa.setAge(age);
		pa.setPhone(phone);
		if(passengerService.createPassenger(pa) ==  null)
		{
			System.out.println("Inside create if");
			SpecialException e = new SpecialException();
			e.setCode(404);
			e.setMessage("Another person with same number already exists");
			throw e;
		}
		return passengerService.createPassenger(pa);
	}
	
	@PutMapping("/passenger/{id}")
	public Passenger updatePassenger(@PathVariable int id, @RequestParam(value="firstname", required=true) String firstName, @RequestParam(value="lastname", required=true) String lastName, @RequestParam(value="age", required=true) int age, @RequestParam(value="gender", required=true) String gender, @RequestParam(value="phone", required=true) String phone) throws Exception{
		if(passengerService.updatePassenger(id, firstName, lastName, age, gender, phone) ==  null)
		{
			System.out.println("Inside update if");
			SpecialException e = new SpecialException();
			e.setCode(404);
			e.setMessage("Sorry the passenger could not be updated");
			throw e;
		}
		return passengerService.updatePassenger(id, firstName, lastName, age, gender, phone);
	}
	
	@DeleteMapping("/{id}")
	public List<Passenger> deletePassenger(@PathVariable int id){
		passengerService.deletePassenger(id);
		return passengerService.getAllPassengers();
	}
}