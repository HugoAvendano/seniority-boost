package com.havendan.rooftop.academy.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havendan.rooftop.academy.dto.TextDTO;
import com.havendan.rooftop.academy.repository.IRepository;
import com.havendan.rooftop.academy.request.TextRequest;
import com.havendan.rooftop.academy.response.TextAnalyzedResponse;
import com.havendan.rooftop.academy.response.TextResponse;
import com.havendan.rooftop.academy.util.Utils;

@Service
public class TextService implements ITextService {
	
	@Autowired
	private IRepository repository;
	
	@Override
	public TextResponse getTextAnalyzed(long id) {
		
		Optional<TextDTO> optText = this.repository.findByIdAndState(id,true);
		if (optText.isPresent()) {
			TextDTO text = optText.get();
			return new TextResponse(text.getId(),text.getHash(),text.getChars(),text.getResult().toString());
		}else {
			return null;
		}
	}  
	
	@Override
	public TextAnalyzedResponse analizeText(TextRequest req) {
		
		
		try {			
			String text = req.getText();
			int chars = req.getChars();
			String hash = Utils.generateHashMD5(text.concat(String.valueOf(chars)));
			
			Optional<TextDTO> textFounded = this.repository.findByHash(hash);
			if(textFounded.isPresent() && textFounded.get().isState()==true) {
				TextDTO response = textFounded.get();
				return new TextAnalyzedResponse(response.getId(), "/text/" + Long.toString(response.getId()));
				
			}				
			HashMap<String, Integer> testResult = Utils.generateResult(text, chars);
			TextDTO response = this.repository.save(new TextDTO(0, text, chars, testResult.toString(), hash, true));
			return new TextAnalyzedResponse(response.getId(), "/text/" + Long.toString(response.getId()));
			
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	

}
