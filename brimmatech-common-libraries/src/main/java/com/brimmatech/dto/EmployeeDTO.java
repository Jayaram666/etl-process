package com.brimmatech.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	private Integer employeeId;

	private String firstName;

	private String lasstName;

	private String email;

	private LocalDate birthDate;

	private String country;

	private String company;

}
