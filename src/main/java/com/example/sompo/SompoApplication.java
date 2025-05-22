package com.example.sompo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.sompo.entity")
@EnableJpaRepositories(basePackages = "com.example.sompo.repository")
public class SompoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SompoApplication.class, args);
	}
}
