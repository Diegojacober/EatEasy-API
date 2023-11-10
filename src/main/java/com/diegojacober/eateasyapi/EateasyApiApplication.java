package com.diegojacober.eateasyapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.diegojacober.eateasyapi.rest.controller.dto.requests.RegisterRequestDTO;
import com.diegojacober.eateasyapi.rest.service.AuthenticationService;

import static com.diegojacober.eateasyapi.domain.entity.enums.Role.*;

@SpringBootApplication
public class EateasyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EateasyApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service) {
		return args -> {
			// var admin = RegisterRequestDTO.builder()
			// 		.firstname("Admin")
			// 		.lastname("Admin")
			// 		.email("admin@mail.com")
			// 		.password("password")
			// 		.role(ADMIN)
			// 		.build();
			// System.out.println("Admin token: " + service.register(admin).getAccessToken());

			// var manager = RegisterRequestDTO.builder()
			// 		.firstname("Admin")
			// 		.lastname("Admin")
			// 		.email("manager@mail.com")
			// 		.password("password")
			// 		.role(MANAGER)
			// 		.build();
			// System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
