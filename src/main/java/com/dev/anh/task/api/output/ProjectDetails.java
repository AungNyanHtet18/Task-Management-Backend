package com.dev.anh.task.api.output;

import java.time.LocalDate;
import java.util.List;

import com.dev.anh.task.model.entity.Project;

public record ProjectDetails(
		 int id,
		 String name,
		 LocalDate startDate,
		 LocalDate dueDate,
		 String details,
		 List<TaskListItem>tasks) {

	
		public static ProjectDetails from(Project project) {
		    var builder = new Builder();
		    
			builder.id(project.getId())
				   .name(project.getName())
				   .startDate(project.getStartDate())
				   .dueDate(project.getDueDate())
				   .details(project.getDescription())
				   .tasks(project.getTasks().stream().map(TaskListItem::from).toList());
				 
		    return builder.build();
		}
	
		public static class Builder {
			 private int id;
			 private String name;
			 private LocalDate startDate;
			 private LocalDate dueDate;
			 private String details;
			 private List<TaskListItem> tasks;
			
			 public Builder id(int id) {
				  this.id = id;
				  return this;
			 }
			 
			 public Builder name(String name) {
				  this.name = name;
				  return this;
			 }
			 
			 public Builder startDate(LocalDate startDate) {
				  this.startDate = startDate;
				  return this;
			 }
			 
			 public Builder dueDate(LocalDate dueDate) {
				  this.dueDate = dueDate;
				  return this;
			 }
			 
			 public Builder details(String details) {
				  this.details = details;
				  return this;
			 }
			 
			 public Builder tasks(List<TaskListItem> tasks) {
				  this.tasks = tasks;
				  return this;
			 }
			 
			 public ProjectDetails build() {
				  return new ProjectDetails(
						  id, 
						  name, 
						  startDate, 
						  dueDate, 
						  details,
						  tasks);
			 }
			 
		}
}