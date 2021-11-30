package com.example.Posicion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PosicionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosicionApplication.class, args);
	}

	@Bean
	public RestTemplate getresttemplate() {
		return new RestTemplate();
	}

}
