package com.havendan.rooftop.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.havendan.rooftop.academy.exception.ServiceException;
import com.havendan.rooftop.academy.request.TextRequest;
import com.havendan.rooftop.academy.response.ErrorResponse;
import com.havendan.rooftop.academy.service.ITextService;

@RestController
public class TextContoller {
	
	@Autowired
	private ITextService service;
	
	@GetMapping(path ="text/{id}", produces = "application/json")
	public ResponseEntity<?> getTextById(@PathVariable long id ){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(this.service.getTextAnalyzed(id)) ;
		}catch (ServiceException e){			
			return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e.isError(), e.getCode(), e.getMessage())) ;
		}	
		
	}
	
	@PostMapping(path="text", consumes = "application/json", produces = "application/json" )
	public ResponseEntity<?> analizeText (@RequestBody TextRequest req) {		
		try {
			return ResponseEntity.status(200).body(this.service.analizeText(req));
		} catch (ServiceException e) {			
			return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e.isError(), e.getCode(), e.getMessage())) ;
		}
	}
	
	@DeleteMapping(path ="text/{id}", produces = "application/json")
	public ResponseEntity<?> deleteText (@PathVariable long id){
		try {
			this.service.deleteString(id);
			return ResponseEntity.status(200).body("{}");
		} catch (ServiceException e) {			
			return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e.isError(), e.getCode(), e.getMessage())) ;
		}
		
	}
	
	@GetMapping(path = "text", produces = "application/json")
	public ResponseEntity<?> getTextById(@RequestParam (required = false, defaultValue = "2") Integer chars, @RequestParam  (required = false, defaultValue = "1") Integer page, @RequestParam  (required = false,  defaultValue = "10") Integer rpp ){
		try {
			return ResponseEntity.status(200).body(this.service.findByCharsPagination(chars,page, rpp));
		} catch (ServiceException e) {
			return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e.isError(), e.getCode(), e.getMessage())) ;
		}	
				
		
	}
	
	
	
 	
}
