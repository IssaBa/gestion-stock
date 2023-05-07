package com.example.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	CommandLineRunner commandLineRunner (
			StudentRepository studentRepository
			) {
		return args->{
			Student issa =new Student(
					1,
					"issa",
					"issa.ba@gmail.com",
					LocalDate.of(2000, Month.JANUARY, 5));
		
			Student isac = new Student(
					2,
					"naar",
					"naar.douBain@gmail.com",
					LocalDate.of(1991, Month.OCTOBER, 5));
			
			studentRepository.saveAll(List.of(issa,isac));
			
		};
		
		
	}

}
