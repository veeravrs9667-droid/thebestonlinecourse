package com.example.ocm.dto;



public class CourseDTO {

	private Long id ;
	private String title;
	private Double price;
	private String description;
	private int capacity;
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public CourseDTO() {};
	
	public CourseDTO(Long id, String title,Double price,String description) {
	
		this.id=id;
		this.title=title;
		this.price=price;
		this.description=description;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	private String thumbnail;
	
}
