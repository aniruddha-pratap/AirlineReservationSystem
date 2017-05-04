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

//    @GetMapping(value="/")
//    public Reservation searchReservation(@RequestParam(value = "passengerId") int passengerID,@RequestParam(value="from") String from, @RequestParam(value="to") String to,
//                                         @RequestParam(value="flightNumber") String flightNumber){
//
//        return reservationService.searchReservation(passengerID,from,to,flightNumber);
//    }
}
