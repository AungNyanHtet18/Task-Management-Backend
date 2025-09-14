package com.dev.anh.task.api.input;

import java.time.LocalDate;

import com.dev.anh.task.model.entity.Project;

import jakarta.validation.constraints.NotBlank;

public record ProjectForm(
	@NotBlank
	String name,
	String error,
	LocalDate startDate,
	LocalDate dueDate,
	String description
	) {

	public Project entity() {
		var entity = new Project();
		update(entity);
		return entity;
	}

	public void update(Project entity) {
		entity.setName(name);
		entity.setStartDate(startDate);
		entity.setDueDate(dueDate);
		entity.setDescription(description);
	}
	

}
