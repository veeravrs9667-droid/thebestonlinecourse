package com.example.ocm.dto;



public class EnrollementResponseDto {

	  private StudentDTO student;
	  
	 
	private CourseDTO course;
	

	    public StudentDTO getStudent() {
		return student;
	}


	public void setStudent(StudentDTO student) {
		this.student = student;
	}


	public CourseDTO getCourse() {
		return course;
	}


	public void setCourse(CourseDTO course) {
		this.course = course;
	}


		public EnrollementResponseDto(StudentDTO student, CourseDTO course) {
	        this.student = student;
	        this.course = course;
	    }
	    
}
