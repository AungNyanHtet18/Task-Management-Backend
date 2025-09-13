package com.dev.anh.task.api.input;

import java.time.LocalDate;

public record ProjectSearch(
	LocalDate startFrom,
	LocalDate startTo,
	LocalDate dueDateFrom,
	LocalDate dueDateTo,
	Long taskFrom,
	Long taskTo,
	String keyword){

}
