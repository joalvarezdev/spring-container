package com.joalvarez.springcontainer.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.springcontainer.data.dto.RoleDTO;
import com.joalvarez.springcontainer.data.mapper.generals.BaseMapper;
import com.joalvarez.springcontainer.data.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper extends BaseMapper<RoleDTO, Role> {

	public RoleMapper(ObjectMapper objectMapper) {
		super(objectMapper);
	}
}
