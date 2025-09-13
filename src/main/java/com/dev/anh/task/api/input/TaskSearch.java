package com.dev.anh.task.api.input;

import java.time.LocalDate;

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

}
