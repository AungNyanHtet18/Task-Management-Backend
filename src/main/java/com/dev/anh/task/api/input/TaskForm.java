package com.dev.anh.task.api.input;

import java.time.LocalDate;

import com.dev.anh.task.model.entity.Project;
import com.dev.anh.task.model.entity.Task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskForm(
	@NotNull(message = "Please select project.")
	Integer projectId,
	@NotBlank(message = "Please enter task name.")
	String name,
	@NotBlank(message = "Please enter assignee.")
	String assignee,
	@NotNull(message = "Please enter due date")
	LocalDate dueDate,
	LocalDate startDate,
	LocalDate endDate,
	String descripString
	) {

	public Task entity(Project project) {
		var entity = new Task();
		entity.setProject(project);
		update(entity);
		return entity;
	}
	
	public void update(Task entity) {
		entity.setName(name);
		entity.setAssignee(assignee);
		entity.setDueDate(dueDate);
		entity.setStartDate(startDate);
		entity.setEndDate(endDate);
		entity.setDescription(descripString);
	}

}
