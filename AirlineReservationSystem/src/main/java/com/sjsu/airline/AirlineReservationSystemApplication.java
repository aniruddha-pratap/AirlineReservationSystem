package com.sjsu.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.sjsu.airline.repositories")
@EntityScan(basePackages = "com.sjsu.airline.Passengers")
@SpringBootApplication
public class AirlineReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineReservationSystemApplication.class, args);
	}
}
