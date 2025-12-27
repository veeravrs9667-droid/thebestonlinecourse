package com.example.ocm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ocm.dto.CourseDTO;
import com.example.ocm.dto.EnrollementResponseDto;
import com.example.ocm.dto.StudentDTO;
import com.example.ocm.entity.Course;
import com.example.ocm.entity.Student;
import com.example.ocm.mapper.StudentMapper;
import com.example.ocm.repository.CourseRepository;
import com.example.ocm.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	

	public StudentDTO create(StudentDTO dto) {
		
		  List<Course> coursesList = new ArrayList<>();

	        if (dto.getCourseIds() != null && !dto.getCourseIds().isEmpty()) {
	            coursesList = courseRepo.findAllById(dto.getCourseIds());
	        }
		
		Student student = StudentMapper.toEntity(dto, coursesList);
		Student saved = studentRepo.save(student);
		
		return StudentMapper.toDTO(saved);
	}
	
	public List<StudentDTO> getAll() {
		
		return studentRepo.findAll().stream().map(StudentMapper::toDTO).toList();
	}
	
	public StudentDTO getById(Long id ) {
		
		return studentRepo.findById(id).map(StudentMapper::toDTO).orElse(null);
	
	}
	
	public StudentDTO update(Long id,StudentDTO dto) {
		
	return	studentRepo.findById(id).map(existing ->{
			existing.setName(dto.getName());
			existing.setEmail(dto.getEmail());
			List<Course> updatedCourses = courseRepo.findAllById(dto.getCourseIds());
			existing.setCourses(updatedCourses);
			
			Student saved = studentRepo.save(existing);
			return StudentMapper.toDTO(saved);
		}).orElse(null);
	}
	
	public void delete(Long id) {
		 studentRepo.deleteById(id);
	}
	
	public EnrollementResponseDto enroll(Long studentId,Long courseId) {
		
		Student student = studentRepo.findById(studentId)
				.orElseThrow(()-> new RuntimeException("student Not Found"));
		
		Course course = courseRepo.findById(courseId)
				.orElseThrow(()-> new RuntimeException("course not found"));
		if(student.getCourses().contains(course)) {
			 throw new RuntimeException("Student already enrolled");
		}
		if(course.getStudents().size()>= course.getCapacity()) {
			 throw new RuntimeException("Selected Course is Full");
		}
		
		student.getCourses().add(course);
		course.getStudents().add(student);
	      studentRepo.save(student);
	      
	      return new EnrollementResponseDto(
	    		  new StudentDTO(student.getId(),student.getName(),student.getEmail(),null),
	    		  new CourseDTO(course.getId(),course.getTitle(),course.getPrice(),course.getDescription())
	    		  );
	}
	
}
