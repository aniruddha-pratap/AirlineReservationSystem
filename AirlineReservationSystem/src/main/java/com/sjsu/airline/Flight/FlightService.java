package com.sjsu.airline.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import com.sjsu.airline.repositories.FlightRepository;
import java.util.*;

/**
 * Created by Vivek Agarwal on 4/25/2017.
 */
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights(){ return flightRepository.findAll(); }

    public boolean saveOrUpdateFlight(Flight flight){
        try{
            flightRepository.save(flight);
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

