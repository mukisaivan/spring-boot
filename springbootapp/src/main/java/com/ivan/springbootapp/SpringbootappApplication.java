package com.ivan.springbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SpringbootappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootappApplication.class, args);
	}

}
/*-
public class SpringbootappApplication implements CommandLineRunner {
@Autowired
EmployeeRepositoryClass employeeRepository;
@Override
public void run(String... args) throws Exception {
	System.out.println("Adding test employee");
	EmployeeModel testEmployee = new EmployeeModel(
		"mukisa",
		"Tracy",
		LocalDate.now());
		employeeRepository.save(testEmployee);
		System.out.println("Finished Adding test employee");
		}
	*/