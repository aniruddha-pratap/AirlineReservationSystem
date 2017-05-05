package com.sjsu.airline.Reservations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(value="/{id}")
    public Reservation getReservation(@PathVariable int id, @RequestParam String xml){
        Reservation reservation=reservationService.getReservation(id);
        return reservation;
    }

    @PostMapping(value="/")
    public Reservation reserve(@RequestParam(value = "passengerId") int passengerID,@RequestParam(value="flightLists") List<String> flightLists){
        return reservationService.makeReservation(passengerID,flightLists);
    }

    @GetMapping(value="/")
    public List<Reservation> searchReservation(@RequestParam(value = "passengerId", required = false) Integer passengerID,@RequestParam(value="from",required = false) String from, @RequestParam(value="to", required = false) String to,
                                         @RequestParam(value="flightNumber",required = false) String flightNumber){

        return reservationService.searchReservation(passengerID,from,to,flightNumber);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteReservation(@PathVariable Integer id){
        return reservationService.deleteReservation(id);
    }
}
