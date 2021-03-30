package com.brimmatech.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BussinessValidationDTO {

	private List<EmployeeValidationDTO> employeeValidationDTO=new ArrayList<EmployeeValidationDTO>();

}
