package com.joalvarez.springcontainer.service.interfaces;

import com.joalvarez.springcontainer.service.generals.IBaseService;

public interface IUserService<DTO> extends IBaseService<DTO, Long>{

	DTO register(DTO dto);
}
