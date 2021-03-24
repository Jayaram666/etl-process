package brimmatech.etl.process.client;


import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;


public interface IValidationClientService {

	BussinessValidationDTO validateEmployeeAge(EmployeeDTO employee);

}
