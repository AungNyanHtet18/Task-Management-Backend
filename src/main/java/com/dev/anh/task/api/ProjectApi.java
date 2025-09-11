package com.dev.anh.task.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.anh.task.api.input.ProjectForm;
import com.dev.anh.task.api.input.ProjectSearch;
import com.dev.anh.task.api.output.ModificationResult;
import com.dev.anh.task.api.output.ProjectDetails;
import com.dev.anh.task.api.output.ProjectListItem;

@RestController
@RequestMapping("projects")
public class ProjectApi {
	
	/**
	 * Get -> /projects
	 * @param search
	 * @return
	 */
	@GetMapping
	List<ProjectListItem> search(ProjectSearch search) {
		 return null;
	}
	
	/**
	 * Get -> /projects/10
	 * @param id
	 * @return
	 */
	@GetMapping("{id}")
	ProjectDetails findById(@PathVariable int id) {
		return null;
	}
	
	
	ModificationResult<Integer> create(@RequestBody ProjectForm form) {
		 return null;
	}
	
}
