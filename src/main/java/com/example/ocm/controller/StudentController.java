package com.example.ocm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ocm.dto.StudentDTO;
import com.example.ocm.entity.Course;
import com.example.ocm.service.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@PostMapping
	public StudentDTO create(@RequestBody StudentDTO dto) {
		return service.create(dto);
	}
	
	@GetMapping
	public List<StudentDTO> getAll(){
		return service.getAll();
	}
	
	@GetMapping("/{id}")
    public StudentDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }
	
	@PutMapping("/{id}")
	public StudentDTO update(@PathVariable Long id, @RequestBody StudentDTO dto ) {
		
		return service.update(id,dto);
	}
	
	@PostMapping("enroll/student/{studentId}/course/{courseId}")
	public ResponseEntity<String> enroll(@PathVariable Long studentId, @PathVariable Long courseId){
		
		service.enroll(studentId,courseId);
		return ResponseEntity.ok("Successfully enrolled");
	}
	
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	
}
