package com.dev.anh.task.api.output;

import java.time.LocalDate;

import com.dev.anh.task.model.entity.Task;

public record TaskDetails(
	int id,
	ProjectListItem project,
	String name,
	String assignee,
	LocalDate dueDate,
	LocalDate startDate,
	LocalDate endDate,
	String description) {

	public static TaskDetails from(Task entity) {
		return new TaskDetails(
				entity.getId(),
				ProjectListItem.from(entity.getProject()),
				entity.getName(),
				entity.getAssignee(),
				entity.getDueDate(),
				entity.getStartDate(),
				entity.getEndDate(),
				entity.getDescription()
			   );
	}

}
