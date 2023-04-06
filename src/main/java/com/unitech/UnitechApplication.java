package com.unitech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UnitechApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnitechApplication.class, args);
	}

}
