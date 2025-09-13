package com.dev.anh.task.api.output;

import java.time.LocalDate;

public record TaskDetails(
	int id,
	ProjectListItem project,
	String name,
	String assignee,
	LocalDate dueDate,
	LocalDate startDate,
	LocalDate endDate,
	String description) {

}
