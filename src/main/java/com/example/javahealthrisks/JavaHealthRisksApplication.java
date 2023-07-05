package com.example.javahealthrisks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavaHealthRisksApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaHealthRisksApplication.class, args);
	}

}
