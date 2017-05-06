/* Work on returning error messages */


package com.sjsu.airline.Flight;

import com.sjsu.airline.Exception.SpecialException;
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
    public List<Flight> getAllFlights(@RequestParam(value="xml") String xml) throws SpecialException {

        List<Flight> results= flightService.getAllFlights();
        if(results==null){
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("Could not find flights.");
            throw e;
        }
        return results;
    }

    @GetMapping(value = "/{id}")
    public Flight getFlight(@PathVariable String id) throws SpecialException {

        Flight flightResult= flightService.getFlight(id);
        if(flightResult==null){
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("No flight detected for id "+id);
            throw e;
        }
        return flightResult;
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
                                      ) throws SpecialException {

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

        Flight flightResult= flightService.saveOrUpdateFlight(flight);

        if(flightResult==null){
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("Could not update flight. Please ensure flight exists and check for overlap.");
            throw e;
        }
        return flightResult;
    }

    @DeleteMapping("/{id}")
    public boolean deleteFlight(@PathVariable String id) throws SpecialException {

        if(!flightService.deleteFlight(id)){
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("Could not delete flight. Please ensure flight id is correct and no passengers have booked this flight.");
            throw e;
        }
        return true;
    }
}
