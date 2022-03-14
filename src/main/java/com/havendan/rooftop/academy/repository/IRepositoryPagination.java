package com.havendan.rooftop.academy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.havendan.rooftop.academy.dto.TextDTO;

public interface IRepositoryPagination extends PagingAndSortingRepository<TextDTO, Long> {
	
	public Page<TextDTO> findByCharsAndState (int chars, boolean state, Pageable pageReq);
	
}
