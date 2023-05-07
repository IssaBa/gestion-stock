package Employer.angular.SpringBoot.services;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Employer.angular.SpringBoot.model.Employee;
import Employer.angular.SpringBoot.repository.EmployeeRepository;

@Service
public class AuthDetailsService implements UserDetailsService{
	
	@Autowired
    EmployeeRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
			Employee emp = repository.findByUsername(username);
			// functionalité de spring sécurity
			return new org.springframework.security.core.userdetails.User(emp.getEmailId(),emp.getPassword(),new ArrayList<>()) ;
			
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}

}
