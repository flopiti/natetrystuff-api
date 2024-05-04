package com.natetrystuff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.natetrystuff.Meal, package com.natetrystuff,  com.natetrystuff.MealSchedule"})
public class NateTryStuffApplication {

	public static void main(String[] args) {
		SpringApplication.run(NateTryStuffApplication.class, args);
	}

}
