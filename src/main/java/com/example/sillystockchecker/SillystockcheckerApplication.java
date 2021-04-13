package com.example.sillystockchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SillystockcheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SillystockcheckerApplication.class, args);
	}

}
