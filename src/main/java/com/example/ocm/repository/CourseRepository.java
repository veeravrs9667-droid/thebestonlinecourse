package com.example.ocm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ocm.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // For paginated search
    Page<Course> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    
}
