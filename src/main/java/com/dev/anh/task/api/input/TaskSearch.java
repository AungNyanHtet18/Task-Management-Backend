package com.dev.anh.task.api.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.dev.anh.task.model.entity.Project_;
import com.dev.anh.task.model.entity.Task;
import com.dev.anh.task.model.entity.Task_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record TaskSearch(
	Integer projectId,
	LocalDate dueDateFrom,
	LocalDate dueDateTo,
	LocalDate startFrom,
	LocalDate startTo,
	LocalDate endFrom,
	LocalDate endTo,
	String keyword
	) {

	public Predicate[] where(CriteriaBuilder cb, Root<Task> root) {
		var params = new ArrayList<Predicate>();
		
		if(null != projectId) {
			 params.add(cb.equal(root.get(Task_.project).get(Project_.id), projectId));
		}
		
		if(null != dueDateFrom) {
			 params.add(cb.greaterThanOrEqualTo(root.get(Task_.dueDate), dueDateFrom));
		}
		
		if(null != dueDateTo ) {
			 params.add(cb.lessThan(root.get(Task_.dueDate), dueDateTo));
		}
		
		if(null != startFrom) {
			 params.add(cb.greaterThanOrEqualTo(root.get(Task_.startDate), startFrom));
		}
		
		if(null != endFrom) {
			 params.add(cb.greaterThanOrEqualTo(root.get(Task_.endDate), endFrom));
		}
		
		if(null != endTo) {
			 params.add(cb.lessThan(root.get(Task_.endDate), endTo));
		}
		
		if(StringUtils.hasLength(keyword)) {
			 params.add(cb.or(
				  cb.like(cb.lower(root.get(Task_.name)),  keyword.toLowerCase().concat("%")),
				  cb.like(cb.lower(root.get(Task_.description)), keyword.toLowerCase().concat("%"))		  
			 ));
		}
		
		
		return params.toArray(size -> new Predicate[size]);
	}

}
