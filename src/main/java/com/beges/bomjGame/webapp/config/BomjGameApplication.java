package com.beges.bomjGame.webapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages="com.beges.bomjGame")
public class BomjGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(BomjGameApplication.class);
	}

}
