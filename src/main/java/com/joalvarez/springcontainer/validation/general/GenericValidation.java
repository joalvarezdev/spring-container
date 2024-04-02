package com.joalvarez.springcontainer.validation.general;

import com.joalvarez.springcontainer.data.dao.generals.GenericDAO;
import jakarta.validation.ConstraintValidator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.Annotation;

public abstract class GenericValidation<CLA extends Annotation,
	OBJ,
	REP extends JpaRepository<ENT, PK>,
	ENT, PK,
	DAO extends GenericDAO<REP, ENT, PK>>
	implements ConstraintValidator<CLA, OBJ> {

	protected final DAO dao;

	public GenericValidation(DAO dao) {
		this.dao = dao;
	}
}
