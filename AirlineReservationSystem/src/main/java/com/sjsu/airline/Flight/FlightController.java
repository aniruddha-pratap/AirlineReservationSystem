/* Work on returning error messages */


package com.sjsu.airline.Flight;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Flight> getAllFlights(@RequestParam(value="xml", required = false) String xml, @RequestParam(value="json", required=false) String json){
        return flightService.getAllFlights();
    }

    @GetMapping(value = "/{id}")
    public Flight getFlight(@PathVariable String id){
        System.out.println("Request for flight "+id);
        return flightService.getFlight(id);
    }

    @PostMapping(value="/{id}")
    public Flight saveOrUpdateFlight(@PathVariable String id,
                                      @RequestParam(value="price") int price,
                                      @RequestParam(value="from") String from,
                                      @RequestParam(value="to") String to,
                                      @RequestParam(value="departureTime") Date departureTime,
                                      @RequestParam(value="arrivalTime") Date arrivalTime,
                                      @RequestParam(value="description") String description,
                                      @RequestParam(value="capacity") int capacity,
                                      @RequestParam(value="model") String model,
                                      @RequestParam(value="manufacturer") String manufacturer,
                                      @RequestParam(value="yearOfManufacture") int yearOfManufacture
                                      ){

        Flight flight=new Flight();

        flight.setNumber(id);
        flight.setPrice(price);
        flight.setFromSource(from);
        flight.setToDestination(to);
        flight.setDepartureTime(departureTime);
        flight.setArrivalTime(arrivalTime);
        flight.setDescription(description);
        flight.setCapacity(capacity);
        flight.setSeatsLeft(capacity);
        flight.setModel(model);
        flight.setManufacturer(manufacturer);
        flight.setYearOfManufacture(yearOfManufacture);

        System.out.println(flight.toString());

        return flightService.saveOrUpdateFlight(flight);
    }

    @DeleteMapping("/{id}")
    public boolean deleteFlight(@PathVariable String id){
        return flightService.deleteFlight(id);
    }
}
