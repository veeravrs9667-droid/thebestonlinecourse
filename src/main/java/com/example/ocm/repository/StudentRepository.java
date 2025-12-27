package com.example.ocm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ocm.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long > {

	
}
