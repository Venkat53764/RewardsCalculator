package com.task.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"com.task.app.controllers", "com.task.app.services"})
@EntityScan(basePackages="com.task.app.domain")
@EnableJpaRepositories(basePackages="com.task.app.repositories")
public class RewardsCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RewardsCalculatorApplication.class, args);
	}

}
