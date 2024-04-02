package com.joalvarez.springcontainer.data.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joalvarez.springcontainer.data.dto.UserDTO;
import com.joalvarez.springcontainer.data.mapper.generals.BaseMapper;
import com.joalvarez.springcontainer.data.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends BaseMapper<UserDTO, User> {

	private final RoleMapper roleMapper;

	public UserMapper(ObjectMapper objectMapper, RoleMapper roleMapper) {
		super(objectMapper);
		this.roleMapper = roleMapper;
	}

	@Override
	public User fromDTO(UserDTO entity) {
		User user = super.fromDTO(entity);
		user.setPassword(entity.getPassword());

		user.setRoles(entity.getRoles().stream()
			.map(this.roleMapper::fromDTO)
			.toList());

		return user;
	}
}
