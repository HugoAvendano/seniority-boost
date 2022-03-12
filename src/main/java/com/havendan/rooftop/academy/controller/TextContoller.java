package com.havendan.rooftop.academy.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.havendan.rooftop.academy.request.TextRequest;
import com.havendan.rooftop.academy.response.TextResponse;
import com.havendan.rooftop.academy.service.ITextService;

@RestController
public class TextContoller {
	
	@Autowired
	private ITextService service;
	
	@GetMapping(path ="text/{id}", produces = "application/json")
	public ResponseEntity<?> getTextById(@PathVariable long id ){		
		return ResponseEntity.status(HttpStatus.OK).body(service.getTextAnalyzed(id)) ;
	}
	
	@PostMapping(path="text", consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> analizeText (@RequestBody TextRequest req) {		
		return ResponseEntity.status(200).body(service.analizeText(req));
	}
	
	
	
 	
}
