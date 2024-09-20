package com.kinglsey.movie_auth_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class MovieAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAuthServiceApplication.class, args);
	}

	@RestController
	@RefreshScope
	static class MovieAuthController {
		@Value("${spring.datasource.url}")
		private String dbUrl;

		@GetMapping("/dbUrl")
		public String dbUrl() {
			return dbUrl;
		}
	}
}
