package brimmatech.etl.process;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import brimmatech.etl.process.config.BeanValidator;
import brimmatech.etl.process.domain.EmployeeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest

public class EmployeeBranValidatorTest {
	@Autowired
	private BeanValidator<EmployeeEntity> validator;


	@Test
	public void testInvalidProduct() {

		EmployeeEntity employee = new EmployeeEntity(1212, 1212, "Jay", "Ram", "aaa@Bbb", LocalDate.of(2009, 02, 02),
				"india", "comp", false, false,JsonNodeFactory.instance.objectNode(), "RR", LocalDateTime.now(), LocalDateTime.now());
		System.out.println("The employee is " + employee);
				assertThrows(ValidationException.class, () -> validator.validate(employee));


	}

}
