package com.helmes.form.validator;

import com.helmes.form.model.Person;
import com.helmes.form.service.PersonServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersonFormValidator implements Validator {

	@Autowired
	PersonServiceInterface personService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Person.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Person person = (Person) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.personForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.personForm.lastName");

		if (person.getSectors() == null || person.getSectors().size() < 1) {
			errors.rejectValue("sectors", "Valid.personForm.sectors");
		}

		if (!person.isAcceptTerms()) {
			errors.rejectValue("acceptTerms", "Valid.personForm.acceptTerms");
		}
	}
}
