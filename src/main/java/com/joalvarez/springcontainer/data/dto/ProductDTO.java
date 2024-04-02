package com.joalvarez.springcontainer.data.dto;

import com.joalvarez.springcontainer.data.dto.generals.BaseDTO;

public class ProductDTO implements BaseDTO {

	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
