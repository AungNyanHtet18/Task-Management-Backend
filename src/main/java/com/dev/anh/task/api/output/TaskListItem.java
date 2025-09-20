package com.dev.anh.task.api.output;

import java.time.LocalDate;

import com.dev.anh.task.model.entity.Project_;
import com.dev.anh.task.model.entity.Task;
import com.dev.anh.task.model.entity.Task_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record TaskListItem(
	int id,
	int projectId,
	String project,
	String name,
	String assignee,
	LocalDate dueDate,
	LocalDate startDate,
	LocalDate endDate
	) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<TaskListItem> cq, Root<Task> root) {

		var project = root.join(Task_.project);
		
		cq.multiselect(
		  root.get(Task_.id),
		  project.get(Project_.id),
		  project.get(Project_.name),
		  root.get(Task_.name),
		  root.get(Task_.assignee),
		  root.get(Task_.dueDate),
		  root.get(Task_.startDate),
		  root.get(Task_.endDate)
		);
	}
	
	public static TaskListItem from(Task task) {
		 return new TaskListItem(
				 task.getId(), 
				 task.getProject().getId(), 
				 task.getProject().getName(), 
				 task.getName(), 
				 task.getAssignee(), 
				 task.getDueDate(), 
				 task.getStartDate(), 
				 task.getEndDate());
	}

}
