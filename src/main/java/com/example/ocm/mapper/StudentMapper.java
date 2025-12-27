package com.example.ocm.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.ocm.dto.StudentDTO;
import com.example.ocm.entity.Course;
import com.example.ocm.entity.Student;

public class StudentMapper {

	public static StudentDTO toDTO(Student s) {
		
       StudentDTO dto = new StudentDTO();
       dto.setId(s.getId());
       dto.setName(s.getName());
       dto.setEmail(s.getEmail());
       
       List<Long> ids = s.getCourses().stream().map(Course::getId).toList();
       dto.setCourseIds(ids);       
       
       
       return dto;
	}
	
      public static Student toEntity(StudentDTO dto, List<Course> courses) {
    	  
    	  Student s= new Student();
    	  s.setId(dto.getId());
    	  s.setName(dto.getName());
    	  s.setEmail(dto.getEmail());
    	  
    	  
    	  
    	  if (courses == null)
              s.setCourses(new ArrayList<>());
          else
              s.setCourses(courses);
    	  
    	  return s;
    	  
      }
      
}
