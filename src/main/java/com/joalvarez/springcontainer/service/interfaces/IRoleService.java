package com.joalvarez.springcontainer.service.interfaces;

import com.joalvarez.springcontainer.service.generals.IBaseService;

import java.util.Optional;

public interface IRoleService<DTO> extends IBaseService<DTO, Long>{

	Optional<DTO> findByName(String name);
}
