package com.havendan.rooftop.academy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;		
	
	private boolean error;
	private Integer code;
	private String message;

}
