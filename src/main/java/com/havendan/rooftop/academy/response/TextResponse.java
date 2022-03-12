package com.havendan.rooftop.academy.response;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class TextResponse {
	private long id;
	private String hash;
	private int chars;
	private String resultado;
}
