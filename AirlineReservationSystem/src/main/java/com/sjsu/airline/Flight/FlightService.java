package com.sjsu.airline.Flight;

import com.sjsu.airline.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vivek Agarwal on 4/26/2017.
 */
@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    public boolean saveOrUpdateFlight(Flight flight) {
        try {
            flightRepository.save(flight);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
