package com.dev.anh.task.api.input;

import java.time.LocalDate;

import com.dev.anh.task.model.entity.Project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectForm(
	@NotBlank(message = "Please Enter Project Name")
	String name,
	@NotNull(message = "Please Enter Start Date")
	LocalDate startDate,
	LocalDate dueDate,
	@NotNull(message = "Please Enter Due Date")
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
