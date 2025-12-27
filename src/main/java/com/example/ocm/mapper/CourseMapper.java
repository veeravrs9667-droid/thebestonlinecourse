package com.example.ocm.mapper;

import com.example.ocm.dto.CourseDTO;
import com.example.ocm.entity.Course;

public class CourseMapper {

	public static CourseDTO toDTO(Course c) {
		if(c == null) return null;
		CourseDTO dto = new CourseDTO();
		dto.setId(c.getId());
		dto.setTitle(c.getTitle());
		dto.setPrice(c.getPrice());
		dto.setDescription(c.getDescription());
		dto.setThumbnail(c.getThumbnail());
		dto.setCapacity(c.getCapacity());
		
		return dto;
	}
	
	public static Course toEntity(CourseDTO dto) {
		if(dto == null) return null;
		Course c = new Course();
		c.setId(dto.getId());
		c.setTitle(dto.getTitle());
		c.setPrice(dto.getPrice());
		c.setDescription(dto.getDescription());
		c.setThumbnail(dto.getThumbnail());
		c.setCapacity(dto.getCapacity());
		return c;
	}
}
