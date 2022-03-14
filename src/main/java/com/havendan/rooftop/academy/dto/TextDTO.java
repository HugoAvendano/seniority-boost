package com.havendan.rooftop.academy.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table (name = "Texts")
public class TextDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column (name = "text", nullable=false)
	private String text;
	
	@Column (name = "chars", nullable=false)
	private Integer chars;
	
	@Column (name = "result", nullable=false)
	private String result;
	
	@Column (name = "hash", nullable=false)
	private String hash;
	
	@Column (name = "state", nullable=false)
	private boolean state;
}
