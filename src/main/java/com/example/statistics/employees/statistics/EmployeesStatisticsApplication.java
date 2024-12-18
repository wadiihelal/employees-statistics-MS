package com.example.statistics.employees.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EmployeesStatisticsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeesStatisticsApplication.class, args);
	}

}
