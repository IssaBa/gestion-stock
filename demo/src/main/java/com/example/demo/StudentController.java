package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "api/V1/student")
public class StudentController {

	private  final StudentService serviceStudent;
	
	@Autowired
	public StudentController(StudentService serviceStudent) {
		this.serviceStudent = serviceStudent;
	}
	
	@GetMapping
	public List<Student> getStudents() {
		return serviceStudent.getStudents();
	}
	
	@PostMapping
	public void registrerNewStudent(@RequestBody Student student) {
		serviceStudent.addNewStuent(student);
	}
	
	@DeleteMapping(path = "{idStudent}")
	public void deleteStudent(@PathVariable("idStudent") Long idStudent) {
		serviceStudent.deleteStudent(idStudent);
	}
	
	@PutMapping(path = "{idStudent}")
	public void updateStudent(@PathVariable("idStudent")  Long idStudent,
							@RequestParam(required=false) String name,
							@RequestParam(required = false) String email) {
		
		serviceStudent.updateStudent(idStudent, email, name);
		
		
	}
}
