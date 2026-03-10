package com.invoice.backd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackdApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackdApplication.class, args);
	}

}
