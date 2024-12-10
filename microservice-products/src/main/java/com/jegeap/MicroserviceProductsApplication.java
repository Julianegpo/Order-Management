package com.jegeap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProductsApplication.class, args);
	}

}
