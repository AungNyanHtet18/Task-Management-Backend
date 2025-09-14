package com.dev.anh.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.anh.task.api.input.ProjectForm;
import com.dev.anh.task.api.input.ProjectSearch;
import com.dev.anh.task.api.output.ModificationResult;
import com.dev.anh.task.api.output.ProjectDetails;
import com.dev.anh.task.api.output.ProjectListItem;

@Service
public class ProjectService {

	public ModificationResult<Integer> create(ProjectForm form) {
		
		return null;
	}

	public ModificationResult<Integer> update(int id, ProjectForm form) {
		
		return null;
	}
	
	
	public List<ProjectListItem> search(ProjectSearch search) {
		
		return null;
	}

	public ProjectDetails findById(int id) {
		
		return null;
	}

}
