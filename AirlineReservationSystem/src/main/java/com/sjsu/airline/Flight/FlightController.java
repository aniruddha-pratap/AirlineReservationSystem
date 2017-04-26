package com.sjsu.airline.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created by Vivek Agarwal on 4/25/2017.
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/allFlights")
    public List<Flight> getAllFlights(@RequestParam(value="xml") String xml){
        return flightService.getAllFlights();
    }

    @PostMapping(value="/flight/{id}")
    public boolean saveOrUpdateFlight(@PathVariable String id,@RequestBody Flight flight){ // Check for update overlapping with a Passenger Timings
        flight.setId(id);
        return flightService.saveOrUpdateFlight(flight);
    }
}
