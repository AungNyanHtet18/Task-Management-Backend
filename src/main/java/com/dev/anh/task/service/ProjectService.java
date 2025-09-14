package com.dev.anh.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.anh.task.api.input.ProjectForm;
import com.dev.anh.task.api.input.ProjectSearch;
import com.dev.anh.task.api.output.ModificationResult;
import com.dev.anh.task.api.output.ProjectDetails;
import com.dev.anh.task.api.output.ProjectListItem;
import com.dev.anh.task.model.repo.ProjectRepo;


@Service
@Transactional(readOnly = true) //for public method /if i want to override,i set in method level(@Transactional) 
public class ProjectService {

	@Autowired
	private ProjectRepo repo;
	
	@Transactional
	public ModificationResult<Integer> create(ProjectForm form) {
		var entity =  repo.save(form.entity()); //returned entity is in the  managed state
		return ModificationResult.success(entity.getId());
	}
	
	
	@Transactional
	public ModificationResult<Integer> update(int id, ProjectForm form) {
		var entity = repo.findById(id).orElseThrow();
		form.update(entity);
		
		return ModificationResult.success(entity.getId());
	}
	
	
	public List<ProjectListItem> search(ProjectSearch search) {
		
		return null;
	}

	public ProjectDetails findById(int id) {
		
		return null;
	}

}
