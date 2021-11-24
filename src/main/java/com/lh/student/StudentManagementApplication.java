package com.lh.student;

import lombok.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaRepositories
@SpringBootApplication
@EntityScan (basePackages = "com.lh.student.*")
public class StudentManagementApplication{

public static void main( String[] args ){
	SpringApplication.run(StudentManagementApplication.class, args);
}
@Bean
public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {
		@Override
		public void addCorsMappings( @NonNull CorsRegistry registry) {
			registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:4200");
		}
	};
}
}
