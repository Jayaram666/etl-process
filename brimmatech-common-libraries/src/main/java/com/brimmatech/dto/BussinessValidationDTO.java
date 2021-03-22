package com.brimmatech.dto;

import java.util.List;

public class BussinessValidationDTO {

	private List<EmployeeValidationDTO> employeeValidationDTO;

	public BussinessValidationDTO() {
	}

	public List<EmployeeValidationDTO> getEmployeeValidationDTO() {
		return employeeValidationDTO;
	}

	public void setEmployeeValidationDTO(List<EmployeeValidationDTO> employeeValidationDTO) {
		this.employeeValidationDTO = employeeValidationDTO;
	}

}
