package com.joalvarez.springcontainer.data.dao;

import com.joalvarez.springcontainer.data.dao.generals.GenericDAO;
import com.joalvarez.springcontainer.data.model.Role;
import com.joalvarez.springcontainer.data.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleDAO extends GenericDAO<RoleRepository, Role, Long> {

	public RoleDAO(RoleRepository repository) {
		super(repository);
	}

	public Optional<Role> findByName(String name) {
		return this.repository.findByName(name);
	}
}
