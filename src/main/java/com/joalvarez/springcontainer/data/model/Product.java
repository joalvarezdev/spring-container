package com.joalvarez.springcontainer.data.model;

import com.joalvarez.springcontainer.data.model.generals.MultitenantEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "products")
public class Product extends MultitenantEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private UUID userId;
	private String name;

	@Override
	public UUID getUserId() {
		return this.userId;
	}

	@Override
	public void setUserId(UUID userId) {
		this.userId = userId;
	}

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
