package com.sjsu.airline.Flight;

import com.sjsu.airline.Passengers.Passenger;
import com.sjsu.airline.Reservations.Reservation;
import com.sjsu.airline.repositories.FlightRepository;
import com.sjsu.airline.repositories.PassengerRepository;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vivek Agarwal on 4/26/2017.
 */
@Service

public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public Flight saveOrUpdateFlight(Flight flight) {

        if(!flightRepository.exists(flight.getNumber())) {
            try {
                flightRepository.save(flight);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return flight;
        }

        Flight presentFlight=flightRepository.getOne(flight.getNumber());
        List<Passenger> passengers=presentFlight.getPassengers();

        List<Date> newTiming=new ArrayList<>();
        newTiming.add(flight.getArrivalTime());
        newTiming.add(flight.getDepartureTime());

        for(Passenger passenger:passengers){
            if(passenger==null)
                System.out.println("Passenger is null in Flight Service");
            List<Reservation> reservations=passenger.getReservation();
            for(Reservation reservation:reservations){
                List<Flight> bookedFlights=reservation.getFlights();
                for(Flight bookedFlight:bookedFlights){
                    if(bookedFlight != presentFlight){
                        List<Date> reservedTiming=new ArrayList<>();
                        reservedTiming.add(bookedFlight.getArrivalTime());
                        reservedTiming.add(bookedFlight.getDepartureTime());
                        if(overlap(newTiming,reservedTiming))
                            return null;
                    }
                }
            }
        }

        try {
            flightRepository.save(flight);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return flight;
    }

    private boolean overlap(List<Date> newTiming, List<Date> reservedTime) {
        if(newTiming.get(1).compareTo(reservedTime.get(0))<0) return false;
        if(newTiming.get(0).compareTo(reservedTime.get(1))>0) return false;
        return true;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlight(String id) {
        try {
            Flight flight= flightRepository.findOne(id);
            return flight;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteFlight(String id) {
        try {
            flightRepository.delete(id);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Passenger addPassenger(Flight flight, Passenger passenger) {
        if(flight.getSeatsLeft()<=0)
            return null;
        System.out.println("Flight :"+flight+" passenger :"+passenger);
        flight.addPassenger(passenger);
        flight.setSeatsLeft(flight.getSeatsLeft()-1);
        flightRepository.save(flight);
        return passenger;
    }

    public void setReservation(Flight flight,Reservation reservation) {
        flight.setReservation(reservation);
    }
}
