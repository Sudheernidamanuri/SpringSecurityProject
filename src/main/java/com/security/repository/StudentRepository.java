package com.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

	List<Student> findByGender(String gender);
}
