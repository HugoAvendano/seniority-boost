package com.havendan.rooftop.academy.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ErrorResponse {
	private boolean error;
	private Integer code;
	private String message;
}
