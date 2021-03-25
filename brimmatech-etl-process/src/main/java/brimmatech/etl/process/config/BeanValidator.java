package brimmatech.etl.process.config;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanValidator<EmployeeEntity> implements Validator<EmployeeEntity>, InitializingBean {

	@Autowired
	private javax.validation.Validator validator;

	public void afterPropertiesSet() throws Exception {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.usingContext().getValidator();
	}

	public void validate(Object target) throws ValidationException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);
		if (constraintViolations.size() > 0) {
			buildValidationException(constraintViolations);
		}
	}

	private void buildValidationException(Set<ConstraintViolation<Object>> constraintViolations) {
		StringBuilder message = new StringBuilder();
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			message.append(constraintViolation.getMessage() + "\n");
		}
		throw new ValidationException(message.toString());
	}
}
