package brimmatech.etl.process.client;


import org.springframework.http.ResponseEntity;

import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;


public interface IValidationClientService {

	ResponseEntity<BussinessValidationDTO> validateEmployeeAge(EmployeeDTO employee);

}
