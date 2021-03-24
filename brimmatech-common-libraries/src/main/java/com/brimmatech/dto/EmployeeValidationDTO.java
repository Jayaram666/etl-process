package com.brimmatech.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeValidationDTO {

	private String validation;
	private String validationMessage;


}
