package Employer.angular.SpringBoot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Employer.angular.SpringBoot.exception.RessourceNotFoundException;
import Employer.angular.SpringBoot.model.Employee;
import Employer.angular.SpringBoot.repository.EmployeeRepository;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/")
//@RequestMapping
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository ;
	
	//getAllEmployees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	
	// create employee api
	@PostMapping("/employees")
	public Employee createEmployer(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	
	// get employee By Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Employee not exist"+id));
		return ResponseEntity.ok(employee);
		
	}
	
	// get employee By Email and pwd
	    @GetMapping("/employees//{eml}/{pwd}")
		public ResponseEntity<Employee> getEmployeeEMLPWD(@PathVariable String eml ,@PathVariable String pwd) {
			Employee employee = employeeRepository.getEmpByLogin(eml,pwd);
			if(employee ==null) {
				System.out.println("User n'existe pas  !");
				return null;
			}
			else {
				System.out.println("User existe   !"+employee.getFirstName()+employee.getLastName());
				return ResponseEntity.ok(employee);
			
			}
			
		}
	
	// update employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee>  updateEmployee(@PathVariable Long id , @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Employee not exist"+id));
	
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		employee.setPassword(employeeDetails.getPassword());
		employee.setDateNaissance(employeeDetails.getDateNaissance());
		employee.setSexe(employeeDetails.getSexe());
		employee.setPhone(employeeDetails.getPhone());
		Employee employeeUpdate = employeeRepository.save(employee);
		
		return ResponseEntity.ok(employeeUpdate);
			
	}
	
	// delete Employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String ,Boolean>> deleteEmployee(@PathVariable Long id){
			
		Employee employee = employeeRepository.findById(id).orElseThrow(()->new RessourceNotFoundException("Employee not exist "+id));
			employeeRepository.delete(employee);
		
			Map<String , Boolean> response  = new HashMap<>();
			response.put("delete ", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	

}
