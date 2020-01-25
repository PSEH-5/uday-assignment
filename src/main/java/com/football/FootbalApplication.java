package com.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.*")
@SpringBootApplication

public class FootbalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootbalApplication.class, args);
	}

}
