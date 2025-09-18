package com.dev.anh.task.api.output;

import java.time.LocalDate;

import com.dev.anh.task.model.entity.Project;
import com.dev.anh.task.model.entity.Project_;
import com.dev.anh.task.model.entity.Task_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record ProjectListItem(
	 int id,
	 String name,
	 LocalDate startDate,
	 LocalDate dueDate,
	 long tasks) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<ProjectListItem> cq, Root<Project> root) {

		var tasks = root.join(Project_.tasks,JoinType.LEFT);
		
		cq.multiselect(
		  root.get(Project_.id),
		  root.get(Project_.name),
		  root.get(Project_.startDate),
		  root.get(Project_.dueDate),
		  cb.count(tasks.get(Task_.id))
		);
		
		cq.groupBy(
		  root.get(Project_.id),
		  root.get(Project_.name),
		  root.get(Project_.startDate),
		  root.get(Project_.dueDate)
		);
	}
	
}
