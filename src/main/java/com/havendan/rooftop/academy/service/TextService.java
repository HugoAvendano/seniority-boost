package com.havendan.rooftop.academy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.havendan.rooftop.academy.dto.TextDTO;
import com.havendan.rooftop.academy.exception.ServiceException;
import com.havendan.rooftop.academy.repository.IRepository;
import com.havendan.rooftop.academy.repository.IRepositoryPagination;
import com.havendan.rooftop.academy.request.TextRequest;
import com.havendan.rooftop.academy.response.TextAnalyzedResponse;
import com.havendan.rooftop.academy.response.TextResponse;
import com.havendan.rooftop.academy.util.Utils;

@Service
public class TextService implements ITextService {
	
	@Autowired
	private IRepository repository;
	
	@Autowired 
	IRepositoryPagination repositoryPagination;
	
	@Override
	public TextResponse getTextAnalyzed(long id) throws ServiceException {
		
		Optional<TextDTO> optText = this.repository.findByIdAndState(id,true);
		if (optText.isPresent()) {
			TextDTO text = optText.get();
			return new TextResponse(text.getId(),text.getHash(),text.getChars(),Utils.stringToMap(text.getResult()));
		}else {
			throw new ServiceException(true,404,"Text not found");
		}
	}  
	
	@Override
	public TextAnalyzedResponse analizeText(TextRequest req) throws ServiceException {		
		
		try {			
			String text = req.getText();
			Integer chars = validateChars(text, req.getChars()) ;
			String hash = Utils.generateHashMD5(text.concat(String.valueOf(chars)));
			
			Optional<TextDTO> textFounded = this.repository.findByHash(hash);
			if(textFounded.isPresent() && textFounded.get().isState()==true) {
				TextDTO response = textFounded.get();
				return new TextAnalyzedResponse(response.getId(), "/text/" + Long.toString(response.getId()));
				
			}				
			String testResult = Utils.generateResult(text, chars);
			TextDTO response = this.repository.save(new TextDTO(0, text, chars, testResult.toString(), hash, true));
			return new TextAnalyzedResponse(response.getId(), "/text/" + Long.toString(response.getId()));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(true, 402, "An error occurred when processing the text");			
		}
		
		
	}
	
	private int validateChars (String text , Integer chars) {
		if (chars == null) {
			return 2;
		}else if (chars > text.length()) {
			return text.length();			
		}else {
			return chars;
		}
		
	}

	@Override
	public void deleteString(long id) throws ServiceException {
		
		Optional<TextDTO> optText = this.repository.findByIdAndState(id,true);
		if (optText.isPresent()) {
			TextDTO text = optText.get();
			text.setState(false);
			this.repository.save(text);				
		}else {
			throw new ServiceException(true,404,"Text not found");
		}
	}
	
	public List<TextResponse> findByCharsPagination(int chars, int page, int size) throws ServiceException {
		try {
			 int charsPagination = formatCharsPagination(chars);
			 int pagePagination = formatPagePagination(page);
			 int sizePagination = formatSizePagination(size);
			
			Pageable pageReq = PageRequest.of(pagePagination-1, sizePagination);
			Page<TextDTO> pageResult= 	this.repositoryPagination.findByCharsAndState(charsPagination ,true, pageReq);
			
			return formatResponse (pageResult.getContent());
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(true,404,"Text not found");
		}
		
	}
	
	private int formatCharsPagination (int chars) {
		return chars < 2 ? 2: chars;
	}
	
	private int formatPagePagination (int page) {
		return page < 1 ? 1: page;
	}
	
	private int formatSizePagination (int size) {
		if (size <= 10) {
			return 10; 
		}else if (size >= 100) {
			return 100;
		}else {
			return size;
		}
	}

	private List<TextResponse> formatResponse (List<TextDTO> list){
		List<TextResponse> result = new ArrayList<TextResponse>();
		
		for (int i = 0; i < list.size(); i ++ ) {
			
			TextDTO text = list.get(i);
			TextResponse resp = new TextResponse(text.getId(), text.getHash(), text.getChars(), Utils.stringToMap(text.getResult()));
			result.add(resp);
					
		}
		
		return result;
	}

}
