package com.snehangshu.coronavirustracked;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronavirusTrackedApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusTrackedApplication.class, args);
	}

}
