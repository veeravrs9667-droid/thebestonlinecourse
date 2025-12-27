package com.example.ocm.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


@Entity
public class Student {

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}


	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	private String name;
	private String email;
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@ManyToMany
	@JoinTable(
	        name = "student_courses",
	        joinColumns = @JoinColumn(name= "student_id"),
	        inverseJoinColumns = @JoinColumn(name = "course_id")
	    )
	private List<Course> courses = new ArrayList<>();

	


	public Student() {}
	 
	    public Student(Long id, String name,String email) { this.id = id; this.name = name;this.email=email; }
	
}
