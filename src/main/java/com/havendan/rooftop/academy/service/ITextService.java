package com.havendan.rooftop.academy.service;

import com.havendan.rooftop.academy.request.TextRequest;
import com.havendan.rooftop.academy.response.TextAnalyzedResponse;
import com.havendan.rooftop.academy.response.TextResponse;

public interface ITextService {
	
	public TextAnalyzedResponse analizeText(TextRequest req);

	public TextResponse getTextAnalyzed(long id);  
}
