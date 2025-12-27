package com.example.ocm.dto;

import java.util.List;




public class StudentDTO {

	
  private Long id ;
  
  private String name;
  private String email;
  
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

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public List<Long> getCourseIds() {
	return courseIds;
}

public void setCourseIds(List<Long> courseIds) {
	this.courseIds = courseIds;
}

private List<Long> courseIds;
  

   public StudentDTO() {};
   
   public StudentDTO(Long id , String name , String email, List<Long> courseIds) {
	   this.id = id;
	   this.name = name ;
	   this.email = email;
	   this.courseIds = courseIds;
   }
   
}