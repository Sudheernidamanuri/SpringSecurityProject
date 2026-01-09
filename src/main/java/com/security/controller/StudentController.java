package com.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.model.Student;
import com.security.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;


@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	//Generating the CSRF token
	@GetMapping("/csrfToken")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	//Adding a student
	@PostMapping("/addStudent")
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		
		return studentService.addStudent(student);
	}
	
	//Getting all students
	@GetMapping("/getStudents")
	public ResponseEntity<List<Student>> getAllStudents(){
		
		return studentService.getAllStudents();
	}
	
	//Getting Female Students
	@GetMapping("/getFemales")
	public ResponseEntity<List<Student>> getFemaleStudents(){
		
		return studentService.getFemaleStudents();
	}
	
	//Getting Male Students
	@GetMapping("/getMales")
	public ResponseEntity<List<Student>> getMaleStudents(){
		
		return studentService.getMaleStudents();
	}
	
	//Updating Student
	@PutMapping("/updateStudent/{id}")
	public String updateStudent(@PathVariable Long id,@RequestBody Student student) {
		
		return studentService.updateStudent(id, student);
	}
	
	//Deleting Student
	@DeleteMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable Long id) {
		
		return studentService.deleteStudent(id);
	}
}
