package com.joalvarez.springcontainer.validation;

import com.joalvarez.springcontainer.data.dao.UserDAO;
import com.joalvarez.springcontainer.data.model.User;
import com.joalvarez.springcontainer.data.repository.UserRepository;
import com.joalvarez.springcontainer.validation.general.GenericValidation;
import com.joalvarez.springcontainer.validation.interfaces.ExistsByUsernameOrEmail;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class ExistsByUsernameOrEmailValidation extends GenericValidation<ExistsByUsernameOrEmail, String, UserRepository, User, Long, UserDAO> {

	public ExistsByUsernameOrEmailValidation(UserDAO dao) {
		super(dao);
	}

	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		return !this.dao.existsUsernameOrEmail(s, s);
	}
}
