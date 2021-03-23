package com.brimaatech.bussiness.validation.serviceimpl;


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.brimaatech.bussiness.validation.service.IEmployeeValidationService;
import com.brimaatech.bussiness.validation.webresource.BussinessValidationResource;
import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;
import com.brimmatech.dto.EmployeeValidationDTO;


@Service
@PropertySource( "classpath:messages.properties")
public class EmployeeValidationService implements IEmployeeValidationService {

	@Value( "${employee.validation.message}")
	private String ageValidationMessage;

	@Value( "${employee.validation.age}")
	private String validation;

	Logger logger = LoggerFactory.getLogger(BussinessValidationResource.class);


	@Override
	public BussinessValidationDTO validateAge(@RequestBody EmployeeDTO employeeDTO) {
		BussinessValidationDTO bussinessValidationMessagesList = new BussinessValidationDTO();
		List<EmployeeValidationDTO> employeeValidationList = new ArrayList<>();
		EmployeeValidationDTO employeeValidationDTO = new EmployeeValidationDTO();
		if (null != employeeDTO.getBirthDate()) {
			logger.info("validaing the employee age for the employee - {} ", employeeDTO.getFirstName());
			LocalDate today = LocalDate.now();
			Period period = Period.between(employeeDTO.getBirthDate(), today);
			int age = period.getYears();
			String ageValidationMsg = age >= 16 && age <= 60 ? "" : ageValidationMessage;
			employeeValidationDTO.setValidationMessage(ageValidationMsg);
			employeeValidationList.add(employeeValidationDTO);
			bussinessValidationMessagesList.setEmployeeValidationDTO(employeeValidationList);
			logger.info("validating the employee age is completed , the validation message is  - {} ",
					ageValidationMsg);
		}
		return bussinessValidationMessagesList;

	}

}
