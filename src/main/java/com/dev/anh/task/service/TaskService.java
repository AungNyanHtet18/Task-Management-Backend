package com.dev.anh.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.anh.task.api.input.TaskForm;
import com.dev.anh.task.api.input.TaskSearch;
import com.dev.anh.task.api.output.ModificationResult;
import com.dev.anh.task.api.output.TaskDetails;
import com.dev.anh.task.api.output.TaskListItem;

@Service
public class TaskService {

	public List<TaskListItem> search(TaskSearch search) {
		
		return null;
	}

	public TaskDetails findById(int id) {
		
		return null;
	}

	public ModificationResult<Integer> create(TaskForm form) {
		
		return null;
	}

	public ModificationResult<Integer> update(int id, TaskForm form) {
		
		return null;
	}

}
