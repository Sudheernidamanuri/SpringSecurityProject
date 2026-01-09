package com.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.security.model.Student;
import com.security.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	//Add all the students
	public ResponseEntity<String> addStudent(Student student) {
		
		try {
			
		studentRepository.save(student);
		return new ResponseEntity<>("The Student "+student.getName()+" has been added to DB",HttpStatus.CREATED);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("Please check the JSON you are sending",HttpStatus.NOT_ACCEPTABLE);
	}
	
	//Get all the students
	public ResponseEntity<List<Student>> getAllStudents(){
		
		try {
			// if it works it return the student objects along with it's status code
			List<Student> students = new ArrayList<>();
			
			students = studentRepository.findAll();
			return new ResponseEntity<>(students,HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		   // if anything goes wrong then it return empty list along with it's status code
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	//Getting Female students 
	public ResponseEntity<List<Student>> getFemaleStudents(){
		
        try {
			
			// if it works it return the List of Female student objects along with it's status code
        	return new ResponseEntity<>(studentRepository.findByGender("Female"),HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
           // if anything goes wrong then it return empty list along with it's status code
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	//Getting Male students
	public ResponseEntity<List<Student>> getMaleStudents(){
		
		try {
			//if it works it will return the List of Male objects along with it's status code
		return new ResponseEntity<>(studentRepository.findByGender("Male"),HttpStatus.OK);
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
		   // if anything goes wrong then it return empty list along with it's status code
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	//Updating Students By Id
	public String updateStudent(Long id,Student student) {
		
		if(studentRepository.existsById(id)) {
			
			student.setId(id);
			studentRepository.save(student);
			return "The Student of ID:"+student.getId()+" Data has been updated";
		}
		return "Incorrect ID passed Can't Update";
	}
	
	//Deleting Student By Id
	public String deleteStudent(Long id) {
		
		if(studentRepository.existsById(id)) {
			
			studentRepository.deleteById(id);
			
			return "The Student Data has been Deleted";
		}
		
		return "Incorrect ID passed Can't Delete";
	}

}
