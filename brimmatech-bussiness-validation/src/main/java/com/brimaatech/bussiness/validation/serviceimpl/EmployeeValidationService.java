package com.brimaatech.bussiness.validation.serviceimpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.brimaatech.bussiness.validation.service.IEmployeeValidationService;
import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;
import com.brimmatech.dto.EmployeeValidationDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@PropertySource("classpath:messages.properties")
@Slf4j
public class EmployeeValidationService implements IEmployeeValidationService {

	@Value("${employee.validation.message}")
	private String ageValidationMessage;

	@Value("${employee.validation.age}")
	private String validation;

	@Override
	public BussinessValidationDTO validateAge(@RequestBody EmployeeDTO employeeDTO) {
		BussinessValidationDTO bussinessValidationDTo = new BussinessValidationDTO();
		List<EmployeeValidationDTO> employeeValidationList = new ArrayList<>();
		EmployeeValidationDTO employeeValidationDTO = new EmployeeValidationDTO();
		if (null != employeeDTO.getBirthDate()) {
			log.info("validaing the employee age for the employee - {} ", employeeDTO.getFirstName());
			LocalDate today = LocalDate.now();

			Period period = Period.between(employeeDTO.getBirthDate(), today);
			int age = period.getYears();
			String validationMsg = (age >= 16 && age <= 60 )? null : ageValidationMessage;
			employeeValidationDTO.setValidation(validation);
			employeeValidationDTO.setValidationMessage(validationMsg);
			employeeValidationList.add(employeeValidationDTO);
			bussinessValidationDTo.setEmployeeValidationDTO(employeeValidationList);
			if (validationMsg != null) {
				return bussinessValidationDTo;
			}
			log.info("validating the employee age is completed , the validation message is  - {} ", validationMsg);
		}
		return new BussinessValidationDTO();

	}

}
