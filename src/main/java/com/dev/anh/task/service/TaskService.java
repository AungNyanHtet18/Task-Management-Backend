package com.dev.anh.task.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.anh.task.api.input.TaskForm;
import com.dev.anh.task.api.input.TaskSearch;
import com.dev.anh.task.api.output.ModificationResult;
import com.dev.anh.task.api.output.TaskDetails;
import com.dev.anh.task.api.output.TaskListItem;
import com.dev.anh.task.model.entity.Project;
import com.dev.anh.task.model.entity.Project_;
import com.dev.anh.task.model.entity.Task;
import com.dev.anh.task.model.entity.Task_;
import com.dev.anh.task.model.repo.ProjectRepo;
import com.dev.anh.task.model.repo.TaskRepo;
import com.dev.anh.task.utils.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;


@Service
@Transactional(readOnly = true)
public class TaskService {

	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private ProjectRepo projectRepo;
	
	@Transactional
	public ModificationResult<Integer> create(TaskForm form) {
		
		var project = projectRepo.findById(form.projectId())
				  				.orElseThrow(() -> new ApiBusinessException("There is no project with id : %d".formatted(form.projectId())));
		
		checkBusinessRule(form, project);
		
		var entity = taskRepo.save(form.entity(project));
		
		return ModificationResult.success(entity.getId());
	}

	@Transactional
	public ModificationResult<Integer> update(int id, TaskForm form) {
		
		
		var entity = taskRepo.findById(id).orElseThrow(() -> new ApiBusinessException("There is no task with id : %d".formatted(id) ));
		
		checkBusinessRule(form, entity.getProject());
		
		form.update(entity);
		
		return ModificationResult.success(id);
	}
	
	public List<TaskListItem> search(TaskSearch search) {
		return taskRepo.search(queryFunc(search));
	}
	

	private Function<CriteriaBuilder,CriteriaQuery<TaskListItem>> queryFunc(TaskSearch search) {
		return cb->{
			 var cq = cb.createQuery(TaskListItem.class);
			 var root = cq.from(Task.class);
			 
			 TaskListItem.select(cb,cq,root);
			 cq.where(search.where(cb,root));
			 
			 cq.orderBy(
				cb.desc(root.get(Task_.project).get(Project_.id)),
				cb.desc(root.get(Task_.id)));
			 
			 return cq;
		};
	}

	public TaskDetails findById(int id) {
		var entity = taskRepo.findById(id)
							 .orElseThrow(()-> new ApiBusinessException("There is no task with this id : %d".formatted(id)));
		
		return TaskDetails.from(entity);
	}

	private void checkBusinessRule(TaskForm form, Project project) {
		
		if(form.startDate() == null ||
		   form.endDate()  == null ) {
			 throw new ApiBusinessException("Start Date or End Date need to be filled");
		}
		
		if(!form.dueDate().isAfter(form.startDate())) {
			 throw new ApiBusinessException("Due date must be later than start date");
		}
		
		if(form.dueDate().isBefore(project.getStartDate())) { 
			throw new ApiBusinessException("Due date must be later than project start date");
		}
		
		if(null != form.startDate() &&  form.startDate().isBefore(project.getStartDate())) {
			 throw new ApiBusinessException("Start date must be later than project start date");
		}
		
		if(null != form.endDate() && form.endDate().isBefore(project.getStartDate())) {
			 throw new ApiBusinessException("End date must be later than project start date");
		}
	}
}
