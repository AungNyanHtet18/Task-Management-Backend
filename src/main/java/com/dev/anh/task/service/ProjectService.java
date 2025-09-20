package com.dev.anh.task.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.anh.task.api.input.ProjectForm;
import com.dev.anh.task.api.input.ProjectSearch;
import com.dev.anh.task.api.output.ModificationResult;
import com.dev.anh.task.api.output.ProjectDetails;
import com.dev.anh.task.api.output.ProjectListItem;
import com.dev.anh.task.model.entity.Project;
import com.dev.anh.task.model.entity.Project_;
import com.dev.anh.task.model.repo.ProjectRepo;
import com.dev.anh.task.utils.ApiBusinessException;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;


@Service
@Transactional(readOnly = true) //for public method /if i want to override,i set in method level(@Transactional) 
public class ProjectService {

	@Autowired
	private ProjectRepo repo;
	
	@Transactional
	public ModificationResult<Integer> create(ProjectForm form) {
		
		checkBusinessRule(form);
		
		var entity =  repo.save(form.entity()); //returned entity is in the  managed state
		return ModificationResult.success(entity.getId());
	}
	
	@Transactional
	public ModificationResult<Integer> update(int id, ProjectForm form) {
		var entity = repo.findById(id)
						 .orElseThrow(() -> new ApiBusinessException("There is no project with id : %d".formatted(id)));
		
		form.update(entity);
		
		return ModificationResult.success(entity.getId());
	}
	
	public List<ProjectListItem> search(ProjectSearch search) {
		return repo.search(queryFunc(search));
	}

	private Function<CriteriaBuilder,CriteriaQuery<ProjectListItem>> queryFunc(ProjectSearch search) {
		return cb->{
			 var cq = cb.createQuery(ProjectListItem.class);
			 var root = cq.from(Project.class);
			 
			 var tasks =  root.join(Project_.tasks,JoinType.LEFT);
			 
			 ProjectListItem.select(cb, cq, root, tasks);
			 
			 cq.where(search.where(cb,root));
			 
			 var havingPredicate = search.having(cb, root, tasks);
			 
			 if(havingPredicate.length > 0) {
				 cq.having(havingPredicate);
			 }
			 
			 cq.orderBy(cb.desc(root.get(Project_.id)));
			 
			 return cq;
		};
	}

	public ProjectDetails findById(int id) {
		return repo.findById(id).map(ProjectDetails::from).orElseThrow(()-> new ApiBusinessException("There is no project with this id : %d".formatted(id)));
	}
	
	
	private void checkBusinessRule(ProjectForm form) {
		if(!form.dueDate().isAfter(form.startDate())) {
			 throw new ApiBusinessException("Due Date must be later than start date");
		}
	}

}
