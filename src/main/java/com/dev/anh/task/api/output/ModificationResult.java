package com.dev.anh.task.api.output;


public record ModificationResult<ID>(
	ID id,
	boolean success,
	String message
	) {

	
	
}
