package com.example.ocm.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.ocm.dto.CourseDTO;
import com.example.ocm.entity.Course;
import com.example.ocm.mapper.CourseMapper;
import com.example.ocm.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository repo;

    @Autowired
    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    // CREATE COURSE
    public CourseDTO createCourse(CourseDTO dto) {
        Course entity = CourseMapper.toEntity(dto);
        entity.setId(null); // ensure new record
        Course saved = repo.save(entity);
        return CourseMapper.toDTO(saved);
    }

    // GET BY ID
    public CourseDTO getById(Long id) {
        return repo.findById(id)
                .map(CourseMapper::toDTO)
                .orElse(null);
    }

    // PAGINATION + SEARCH
    public Page<CourseDTO> list(int page, int size, String search) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Course> result;

        if (search == null || search.trim().isEmpty()) {
            result = repo.findAll(pageable);
        } else {
            result = repo.findByTitleContainingIgnoreCase(search, pageable);
        }

        return result.map(CourseMapper::toDTO);
    }

    // UPDATE COURSE
    public CourseDTO updateCourse(Long id, CourseDTO dto) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setTitle(dto.getTitle());
                    existing.setDescription(dto.getDescription());
                    existing.setPrice(dto.getPrice());
                    existing.setThumbnail(dto.getThumbnail());

                    Course saved = repo.save(existing);
                    return CourseMapper.toDTO(saved);
                })
                .orElse(null);
    }

    // DELETE COURSE
    public boolean deleteCourse(Long id) {
        if (!repo.existsById(id)) return false;

        repo.deleteById(id); 
        return true;
    }
}
