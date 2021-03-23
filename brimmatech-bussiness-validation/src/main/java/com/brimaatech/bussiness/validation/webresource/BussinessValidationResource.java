package com.brimaatech.bussiness.validation.webresource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.brimaatech.bussiness.validation.service.IEmployeeValidationService;
import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;


@RestController
public class BussinessValidationResource {

	@Autowired
	private IEmployeeValidationService employeeValidationService;


	@PostMapping( "/employee-bussiness-validation")
	public ResponseEntity<BussinessValidationDTO> validateAge(@RequestBody EmployeeDTO employeeDTO) {
		return new ResponseEntity<>(employeeValidationService.validateAge(employeeDTO), HttpStatus.OK);
	}

}
