package com.lh.student.configuration;

import com.lh.student.model.Student;
import com.lh.student.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.APRIL;

@Configuration
public class StudentConfiguration{

	@Bean
		CommandLineRunner commandLineRunner( StudentRepository repository ){
			return args -> {
			Student jan = new Student("Jan", "Kowalski", "JanKowalski@mail.com", LocalDate.of(1998, APRIL, 11));
		
			Student kamil = new Student("Kamil", "Nowak", "Kamil@mail.com", LocalDate.of(2000, Month.APRIL, 15));
			repository.saveAll(List.of(jan, kamil));
		};
	}
}
