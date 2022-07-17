package com.employee.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.employee.entity")
@ComponentScan("com")
@EnableJpaRepositories("com.employee.repository_Dao")
@EnableScheduling
public class EmployeeBackendFrontendAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeBackendFrontendAngularApplication.class, args);
	}

}
