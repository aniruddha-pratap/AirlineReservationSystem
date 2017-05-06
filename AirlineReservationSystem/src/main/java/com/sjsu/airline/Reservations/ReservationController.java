package com.sjsu.airline.Reservations;

import com.sjsu.airline.Exception.SpecialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(value="/{id}")
    public Reservation getReservation(@PathVariable int id, @RequestParam String xml) throws SpecialException {
        Reservation reservation=reservationService.getReservation(id);
        if(reservation==null){
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("Sorry! The requested reservation with id "+id+" does not exist");
            throw e;
        }
        return reservation;
    }

    @PostMapping(value="/")
    public Reservation reserve(@RequestParam(value = "passengerId") int passengerID,@RequestParam(value="flightLists") List<String> flightLists) throws SpecialException {
        Reservation reservation=reservationService.makeReservation(passengerID,flightLists);
        if(reservation==null){
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("Sorry! The requested reservarion for passenger with id "+passengerID+" could not be performed. Either passenger or flight not present or conflict detected.");
            throw e;
        }
        return reservation;
    }

    @GetMapping(value="/")
    public List<Reservation> searchReservation(@RequestParam(value = "passengerId", required = false) Integer passengerID,
                                               @RequestParam(value="from",required = false) String from,
                                               @RequestParam(value="to", required = false) String to,
                                               @RequestParam(value="flightNumber",required = false) String flightNumber) throws SpecialException {

        List<Reservation> reservations= reservationService.searchReservation(passengerID,from,to,flightNumber);
        if(reservations==null){
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("Sorry your search returned no results.");
            throw e;
        }
        return reservations;
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteReservation(@PathVariable Integer id) throws SpecialException {

        if(!reservationService.deleteReservation(id)){
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("Reservation with number "+id+" does not exist ");
            throw e;
        }

        return true;
    }

    @PostMapping(value = "/{id}")
    public Reservation updateReservation(@PathVariable Integer id,
                                         @RequestParam(value = "flightsAdded", required = false) List<String> flightsAdded,
                                         @RequestParam(value = "flightsRemoved", required = false) List<String> flightsRemoved) throws SpecialException {
        if(flightsAdded!=null && flightsAdded.size()==0) {
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("Could not update reservation. Please add flights in the flightsAdded parameter.");
            throw e;

        }
        if(flightsRemoved!=null && flightsRemoved.size()==0){
            SpecialException e = new SpecialException();
            e.setCode(404);
            e.setMessage("Could not update reservation. Please add flights in the flightsRemoved parameter.");
            throw e;
        }

        if (flightsAdded != null) {
            if (!reservationService.addFlights(id, flightsAdded)){
                SpecialException e = new SpecialException();
                e.setCode(404);
                e.setMessage("Could not add flights to reservation. Please check for conflicts before retrying");
                throw e;
            }
        }
        if (flightsRemoved != null) {
            if (!reservationService.removeFlights(id, flightsRemoved)){
                SpecialException e = new SpecialException();
                e.setCode(404);
                e.setMessage("Could not remove flights from reservation.");
                throw e;
            }
        }

        return reservationService.getReservation(id);
    }


}
