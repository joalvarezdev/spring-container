package com.joalvarez.springcontainer.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	private String name;

	public Role() {}

	public Role(String name) {
		this.name = name;
	}

	public Role(Long id, String name) {
		this(name);
		this.roleId = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long id) {
		this.roleId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
