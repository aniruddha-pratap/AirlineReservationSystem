package com.sjsu.airline.Reservations;

import com.sjsu.airline.Flight.Flight;
import com.sjsu.airline.Flight.FlightService;
import com.sjsu.airline.Passengers.Passenger;
import com.sjsu.airline.Passengers.PassengerService;
import com.sjsu.airline.repositories.FlightRepository;
import com.sjsu.airline.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.FieldRetrievingFactoryBean;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Vivek Agarwal on 5/2/2017.
 */

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passengerService;

    public Reservation getReservation(int id) {
        Reservation reservation=null;
        try {
            reservation = reservationRepository.findOne(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return reservation;
    }

    public Reservation makeReservation(int passengerID, List<String> flightLists) {
        List<Flight> flightsToBeReserved=new ArrayList<>();
        int cost=0;
        Passenger passenger=passengerService.getPassenger(passengerID);

        if(passenger==null) {
            System.out.println("Passenger with id :"+passengerID+" not present!");
            return null;
        }

        System.out.println("List of flights------:"+flightLists+"\n\n\n");
        for(String flightId : flightLists){
            Flight flight=flightService.getFlight(flightId);
            if(flight==null) {
                System.out.println("Flight with flightId :"+flightId+" not present!");
                return null;
            }
            cost+=flight.getPrice();
            flightsToBeReserved.add(flight);
        }

        for(int i=0;i<flightsToBeReserved.size();i++){
            for(int j=i+1;j<flightsToBeReserved.size();j++){
                if(flightsToBeReserved.get(i).conflict(flightsToBeReserved.get(j)))
                {
                    System.out.println("Conflict while reserving flight :"+flightsToBeReserved.get(i).toString()+"\n\nand\n\n"+flightsToBeReserved.get(j).toString());
                    return null;
                }
            }
            List<Reservation> otherReservations=passenger.getReservation();
            for(Reservation reservation:otherReservations){
                if(overlap(reservation,flightsToBeReserved.get(i)))
                    return null;
            }
            if(flightService.addPassenger(flightsToBeReserved.get(i),passenger)==null) return null;
        }

        Reservation reservation=new Reservation();

        for(Flight flight:flightsToBeReserved){
            System.out.println("Flight :"+flight+" \n\n");
            reservation.addFlight(flight);
        }

        reservation.setPassenger(passenger);
        reservation.setPrice(cost);

        System.out.println("Passenger who booked this reservation :"+reservation.getPassenger().getFirstname());

        try {
            reservationRepository.save(reservation);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        System.out.print(reservation.getFlights());

        return reservation;
    }

    public List<Reservation> searchReservation(Integer passengerID, String from, String to, String flightNumber) {
        List<Reservation> searchResults=null;
        try {
            searchResults = reservationRepository.findByPassengerIdOrFlightNumberOrFromSourceOrToDestination(passengerID, from, to, flightNumber);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return searchResults;
    }

    public boolean deleteReservation(Integer id) {
        try {
            Reservation reservation=reservationRepository.findOne(id);
            Set<Flight> reservedFlights=reservation.getFlights();
            Flight flights[]=new Flight[reservedFlights.size()];
            int i=0;
            for(Flight flight:reservedFlights)
                flights[i++]=flight;

            for(Flight flight:flights){
                flight.setSeatsLeft(flight.getSeatsLeft()+1);
                flightService.save(flight);
            }
            reservationRepository.delete(id);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean addFlights(Integer id, List<String> flightsAdded) {
        Reservation reservation=reservationRepository.getReservation(id);
        if(reservation==null) return false;

        for(String flightNumber:flightsAdded){
            Flight flight=flightService.getFlight(flightNumber);
            if(flight==null)
                return false;
            if(!overlap(reservation,flight)) {
                List<Reservation> otherReservations=reservation.getPassenger().getReservation();
                for(Reservation otherReservation:otherReservations){
                    if(overlap(otherReservation,flight))
                        return false;
                }
                reservation.addFlight(flight);
            }
            else
                return false;
        }
        try {
            reservationRepository.save(reservation);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean overlap(Reservation reservation, Flight flight) {
        Set<Flight> flights=reservation.getFlights();

        for(Flight bookedFlight:flights){
            if(bookedFlight.conflict(flight))
                return false;
        }

        return true;
    }


    public boolean removeFlights(Integer id, List<String> flightsRemoved) {
        Reservation reservation=reservationRepository.getReservation(id);
        if(reservation==null) return false;
        Set<Flight> bookedFlights=reservation.getFlights();

        for(String flightNumber:flightsRemoved){
            Flight flight=flightService.getFlight(flightNumber);
            if(flight==null || !bookedFlights.contains(flight))
                return false;
            bookedFlights.remove(flight);
        }

        try {
            reservationRepository.save(reservation);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean carefulAddAndRemove(Integer id, List<String> flightsAdded, List<String> flightsRemoved) {

        return false;
    }
}
