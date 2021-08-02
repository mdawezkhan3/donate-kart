package com.example.donatekart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DonateKartApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonateKartApplication.class, args);
	}

}
