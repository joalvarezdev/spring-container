package com.joalvarez.springcontainer.data.dto;

import com.joalvarez.springcontainer.data.dto.generals.BaseDTO;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class ProductDTO implements BaseDTO {

	private Long id;
	private UUID userId;
	@NotBlank(message = "The name field cannot be empty")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
