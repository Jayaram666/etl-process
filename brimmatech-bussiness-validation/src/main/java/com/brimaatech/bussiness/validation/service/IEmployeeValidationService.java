package com.brimaatech.bussiness.validation.service;

import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;

public interface IEmployeeValidationService {

	BussinessValidationDTO validateAge(EmployeeDTO employeeDTO);

}
