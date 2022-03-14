package com.havendan.rooftop.academy.service;

import java.util.List;

import com.havendan.rooftop.academy.exception.ServiceException;
import com.havendan.rooftop.academy.request.TextRequest;
import com.havendan.rooftop.academy.response.TextAnalyzedResponse;
import com.havendan.rooftop.academy.response.TextResponse;

public interface ITextService {
	
	public TextAnalyzedResponse analizeText(TextRequest req) throws ServiceException;

	public TextResponse getTextAnalyzed(long id) throws ServiceException;

	public void deleteString(long id) throws ServiceException; 
	
	public List<TextResponse> findByCharsPagination(int chars,int page, int size) throws ServiceException;
}
