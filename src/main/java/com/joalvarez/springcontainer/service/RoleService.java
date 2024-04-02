package com.joalvarez.springcontainer.service;

import com.joalvarez.springcontainer.data.dao.RoleDAO;
import com.joalvarez.springcontainer.data.dto.RoleDTO;
import com.joalvarez.springcontainer.data.mapper.RoleMapper;
import com.joalvarez.springcontainer.exception.generals.NotImplementedException;
import com.joalvarez.springcontainer.service.generals.GenericService;
import com.joalvarez.springcontainer.service.interfaces.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService extends GenericService<RoleDAO, RoleMapper> implements IRoleService<RoleDTO> {

	public RoleService(RoleDAO roleDAO, RoleMapper mapper) {
		super(roleDAO, mapper);
	}

	@Override
	public List<RoleDTO> findAll() {
		throw new NotImplementedException();
	}

	@Override
	public RoleDTO findById(Long id) {
		throw new NotImplementedException();
	}

	@Override
	public RoleDTO update(RoleDTO roleDTO) {
		throw new NotImplementedException();
	}

	@Override
	public RoleDTO create(RoleDTO roleDTO) {
		throw new NotImplementedException();
	}

	@Override
	public Optional<RoleDTO> findByName(String name) {
		return this.dao.findByName(name)
			.map(this.mapper::toDTO);
	}
}
