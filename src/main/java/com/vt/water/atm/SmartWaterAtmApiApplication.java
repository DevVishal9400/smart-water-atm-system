package com.vt.water.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SmartWaterAtmApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartWaterAtmApiApplication.class, args);
	}

}
