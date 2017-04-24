package com.sjsu.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.sjsu.airline.repositories")
//@ComponentScan(basePackages = {"com.sjsu.airline.Passengers", "com.sjsu.airline.Plane", "com.sjsu.airline.Reservations"})
@EntityScan(basePackages = {"com.sjsu.airline.Passengers", "com.sjsu.airline.Plane", "com.sjsu.airline.Reservations", "com.sjsu.airline.Flight"})
@SpringBootApplication
public class AirlineReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationSystemApplication.class, args);
	}
}
