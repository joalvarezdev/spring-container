package com.joalvarez.springcontainer.data.dao.generals;

import com.joalvarez.springcontainer.data.model.generals.MultitenantEntity;
import com.joalvarez.springcontainer.data.repository.generals.MultitenantRepository;
import com.joalvarez.springcontainer.utils.LocalStorage;

import java.util.List;
import java.util.UUID;

public abstract class MultitenantDAO<REP extends MultitenantRepository<ENT, PK>, ENT extends MultitenantEntity, PK> extends GenericDAO<REP, ENT, PK> {

	private final LocalStorage localStorage;

	public MultitenantDAO(REP repository, LocalStorage localStorage) {
		super(repository);
		this.localStorage = localStorage;
	}

	@Override
	public List<ENT> findAll() {
		return this.repository.findAllByUserId(this.getCurrentUserId());
	}

	@Override
	public ENT findById(PK id) {
		return this.repository.findByIdAndUserId(id, this.getCurrentUserId());
	}

	@Override
	public ENT save(ENT entity) {
		entity.setUserId(this.getCurrentUserId());
		return this.repository.save(entity);
	}

	protected UUID getCurrentUserId() {
		return this.localStorage.getUserDetails().userId();
	}
}
