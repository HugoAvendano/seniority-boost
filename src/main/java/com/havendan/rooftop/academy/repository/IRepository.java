package com.havendan.rooftop.academy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.havendan.rooftop.academy.dto.TextDTO;

public interface IRepository extends JpaRepository<TextDTO, Long> {
	
	public Optional<TextDTO> findByHash(String hash);

	public Optional<TextDTO> findByIdAndState(long id, boolean b);
	
}
