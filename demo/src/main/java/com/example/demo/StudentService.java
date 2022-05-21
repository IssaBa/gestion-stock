package com.example.demo;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component
public class StudentService {

	@Autowired
	private  StudentRepository repository;
	
	
	public StudentService(StudentRepository repository) {
		this.repository = repository;
		
			}

	public List<Student> getStudents() {
		return repository.findAll();
				
	}

	public  void addNewStuent(Student student) {
		
		Optional<Student> optionalStudent = repository.findStudentByEmail(student.getEmail());
		
		if(optionalStudent.isPresent()) {
			throw new IllegalStateException("student taken");
		}
		
		repository.save(student);
		
	}

	public void deleteStudent(Long idStudent) {
		Optional<Student> optionalStudent = repository.findById(idStudent);
		if(optionalStudent.isPresent()) {
			repository.deleteById(idStudent);
		}
		else {
			throw new IllegalStateException("student not existe !!");
		}
		
	}
	
	@Transactional
	public void updateStudent(Long idStudent , String email , String name) {
		
		Student student= repository.findById(idStudent).orElseThrow(()->
				new IllegalStateException("student with id : "+idStudent+" does not exist") );
		
		if(name!=null && name.length()>0 &&  !Objects.equals(name, student.getEmail())) {
			student.setName(name);
		}
		
		if(email!=null && email.length()>0 && !Objects.equals(email, student.getEmail())) {
			Optional<Student> optionalStudent = repository.findStudentByEmail(email);
				if(optionalStudent.isPresent()) {
					throw new IllegalStateException("student taken");
				}
				student.setEmail(email);
			
		}
		
		
	}

	
}
