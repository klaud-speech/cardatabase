package kr.pe.speech.webbiz;

import kr.pe.speech.webbiz.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableJpaAuditing   // CreateAt, LastModifiedAt
@SpringBootApplication
public class WebbizApplication implements CommandLineRunner {

	private static final Logger LOGGER= LoggerFactory.getLogger(WebbizApplication.class);

	@Autowired
	private CarRepository repostory;

	@Autowired
	private OwnerRepository orepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProjectRepository projectRepository;



	public static void main(String[] args) {
		// 이 주석을 추가하면 애플리케이션이 재시작됨.( 정말..?  No..No...)

		SpringApplication.run(WebbizApplication.class, args);
		LOGGER.info("Application started");
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
			LOGGER.info( car.getBrand() + " " + car.getModel() );
		}

		User user1 = new User();
		user1.setUsername("user@llsollu.com" );
		user1.setPassword("$2y$04$VpbFhaNwnnyIUER75dED6Ooof4FWKv4LW3q.zZWcR5.c1Evu70yv2");
		user1.setRole("USER");

		userRepository.save( user1 );

		User user2 = new User();
		user2.setUsername("admin@llsollu.com");
		user2.setPassword("$2y$04$ZNmboHv6ksX6N0bZpx8F3u9uL8cu0SV/cQDOBD/1wLkZaprz2pkyq");
		user2.setRole("ADMIN");

		userRepository.save( user2 );


		Project project1 = new Project();
		project1.setProjectName("www.speech.pe.kr");
		project1.setProjectType("others");
		project1.setUser(user2);
		project1.getUser().getProjectList().add(project1);
		projectRepository.save(project1);

		Project project2 = new Project();
		project2.setProjectName("www.llsollu.com");
		project2.setProjectType("wordpress");
		project2.setUser(user2);
		project2.getUser().getProjectList().add(project2);
		projectRepository.save(project2);




	}
}
