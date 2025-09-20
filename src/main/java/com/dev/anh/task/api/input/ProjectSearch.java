package com.dev.anh.task.api.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.dev.anh.task.model.entity.Project;
import com.dev.anh.task.model.entity.Project_;
import com.dev.anh.task.model.entity.Task;
import com.dev.anh.task.model.entity.Task_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record ProjectSearch(
	LocalDate startFrom,
	LocalDate startTo,
	LocalDate dueDateFrom,
	LocalDate dueDateTo,
	Long taskFrom,
	Long taskTo,
	String keyword){

	public Predicate[] where(CriteriaBuilder cb, Root<Project> root) {

		var params = new ArrayList<Predicate>();
		
	    if(null != startFrom) {
	    	 params.add(cb.greaterThanOrEqualTo(root.get(Project_.startDate),startFrom));
	    }
	    
	    if(null != startTo) {
	    	 params.add(cb.lessThan(root.get(Project_.startDate), startTo.plusDays(1)));
	    }
	    
	    if(null != dueDateFrom) {
	    	 params.add(cb.greaterThanOrEqualTo(root.get(Project_.dueDate), dueDateFrom));
	    }

	    if(null != dueDateTo) {
	    	 params.add(cb.lessThan(root.get(Project_.dueDate), dueDateTo.plusDays(1)));
	    }
	    
	    
	    if(StringUtils.hasLength(keyword)) {
	    	 params.add(cb.or(
	    			 cb.like(cb.lower(root.get(Project_.name)), keyword.toLowerCase().concat("%")),
	    			 cb.like(cb.lower(root.get(Project_.description)), keyword.toLowerCase().concat("%"))
	    	 ));
	    }
	
		return params.toArray(size -> new Predicate[size]);
	}

	public Predicate[] having(CriteriaBuilder cb, Root<Project> root, ListJoin<Project, Task> tasks) {
		var params = new ArrayList<Predicate>();
		
		var taskCount = cb.count(tasks.get(Task_.id));
		
	    if(null != taskFrom) {
	    	 params.add(cb.ge(taskCount, taskFrom));
	    }
	    
	    if(null != taskTo) {
	    	 params.add(cb.le(taskCount, taskTo));
	    }
		
		return params.toArray(size -> new Predicate[size]);
	}

}
