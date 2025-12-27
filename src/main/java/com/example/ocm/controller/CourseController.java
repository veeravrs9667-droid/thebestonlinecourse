package com.example.ocm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ocm.dto.CourseDTO;
import com.example.ocm.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    // CREATE COURSE
    @PostMapping
    public ResponseEntity<CourseDTO> create(@RequestBody CourseDTO dto) {
        CourseDTO created = service.createCourse(dto);
        return ResponseEntity.ok(created);
    }

    // UPDATED PAGINATION + SEARCH ENDPOINT
    @GetMapping
    public ResponseEntity<Page<CourseDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "") String search
    ) {
        Page<CourseDTO> result = service.list(page, size, search);
        return ResponseEntity.ok(result);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> get(@PathVariable Long id) {
        CourseDTO dto = service.getById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }
    
    

    // UPDATE COURSE
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable Long id, @RequestBody CourseDTO dto) {
        CourseDTO updated = service.updateCourse(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    // DELETE COURSE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean ok = service.deleteCourse(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
    


}
