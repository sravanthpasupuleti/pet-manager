package com.petmanager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:messages.properties")
@SpringBootApplication
public class PetManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PetManagerApplication.class, args);
	}
	
}