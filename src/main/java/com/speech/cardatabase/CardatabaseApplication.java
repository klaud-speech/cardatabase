package com.speech.cardatabase;

import com.speech.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {

	private static final Logger logger= LoggerFactory.getLogger(CardatabaseApplication.class);

	@Autowired
	private CarRepository repostory;

	@Autowired
	private OwnerRepository orepository;

	@Autowired
	private UserRepository urepository;


	public static void main(String[] args) {
		// 이 주석을 추가하면 애플리케이션이 재시작됨.( 정말..?  No..No...)

		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application started");
	}

	@Override
	public void run(String... args) throws Exception{
		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Mary", "Robinson");
		orepository.saveAll( Arrays.asList(owner1, owner2));



		repostory.save( new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000, owner1));
		repostory.save( new Car("Nissan", "Leaf", "White", "SSJ-3002", 2019, 29000, owner2));
		repostory.save( new Car( "Toyota", "Prius", "Silver", "KKO-0212", 2020, 39000, owner2));


		for (Car car : repostory.findAll() ){
			logger.info( car.getBrand() + " " + car.getModel() );
		}

		urepository.save( new User("user@llsollu.com", "$2y$04$VpbFhaNwnnyIUER75dED6Ooof4FWKv4LW3q.zZWcR5.c1Evu70yv2", "USER" ));
		urepository.save( new User("admin@llsollu.com", "$2y$04$ZNmboHv6ksX6N0bZpx8F3u9uL8cu0SV/cQDOBD/1wLkZaprz2pkyq", "ADMIN"));



	}

}
