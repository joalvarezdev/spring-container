package com.joalvarez.springcontainer.service;

import com.joalvarez.springcontainer.data.dao.UserDAO;
import com.joalvarez.springcontainer.data.dto.RoleDTO;
import com.joalvarez.springcontainer.data.dto.UserDTO;
import com.joalvarez.springcontainer.data.mapper.UserMapper;
import com.joalvarez.springcontainer.exception.generals.NotImplementedException;
import com.joalvarez.springcontainer.service.generals.GenericService;
import com.joalvarez.springcontainer.service.interfaces.IRoleService;
import com.joalvarez.springcontainer.service.interfaces.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService extends GenericService<UserDAO, UserMapper> implements IUserService<UserDTO> {

	private final IRoleService<RoleDTO> roleService;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserDAO userDAO, UserMapper mapper, RoleService roleService,
					   PasswordEncoder passwordEncoder) {
		super(userDAO, mapper);
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<UserDTO> findAll() {
		throw new NotImplementedException();
	}

	@Override
	public UserDTO findById(Long id) {
		throw new NotImplementedException();
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		throw new NotImplementedException();
	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		throw new NotImplementedException();
	}

	@Override
	public UserDTO register(UserDTO userDTO) {
		Optional<RoleDTO> roleUser = this.roleService.findByName("ROLE_USER");
		List<RoleDTO> roles = userDTO.getRoles();

		roleUser.ifPresent(roles::add);

		if (userDTO.isAdmin()) {
			Optional<RoleDTO> roleAdmin = this.roleService.findByName("ROLE_ADMIN");
			roleAdmin.ifPresent(roles::add);
		}

		if (Objects.isNull(userDTO.getUserId())) {
			userDTO.setUserId(UUID.randomUUID());
		}

		userDTO.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));

		UserDTO userSaved = this.mapper.toDTO(this.dao.save(this.mapper.fromDTO(userDTO)));

		userSaved.setAdmin(userDTO.isAdmin());

		return userSaved;

	}

}
