package com.sjsu.airline.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Vivek Agarwal on 4/26/2017.
 */
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/flightNumber")
    public List<Flight> getFlights(@RequestParam(value = "xml") String xml){
        return flightService.getFlights();
    }

    @PostMapping(value="/{id}")
    public boolean saveOrUpdateFlight(@PathVariable String id, @RequestParam(value = "price") int price, @RequestParam(value = "from") String from, @RequestParam(value = "to") String to, @RequestParam(value = "departureTime") Date departureTime, @RequestParam(value = "arrivalTime") Date arrivalTime, @RequestParam(value = "description") String description, @RequestParam(value = "capacity") int capacity, @RequestParam(value = "model") String model, @RequestParam(value = "manufacturer") String manufacturer, @RequestParam(value = "yearOfManufacture") int yearOfManufacture){ // Check for update overlapping with a Passenger Timings
        Flight flight=new Flight();
        flight.setNumber(id);
        flight.setSeatsLeft(capacity);
        flight.setPrice(price);
        flight.setFromSource(from);
        flight.setToDestination(to);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setDescription(description);
        flight.setModel(model);
        flight.setManufacturer(manufacturer);
        flight.setYearOfManufacture(yearOfManufacture);

        return flightService.saveOrUpdateFlight(flight);
    }
}
