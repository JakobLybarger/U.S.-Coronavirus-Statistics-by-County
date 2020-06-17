package com.lybarger.jakob.USCoronavirusData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UsCoronavirusDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsCoronavirusDataApplication.class, args);
	}

}
