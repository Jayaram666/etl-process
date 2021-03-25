package brimmatech.etl.process.config;

import org.modelmapper.ModelMapper;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import brimmatech.etl.process.client.IValidationClientService;
import brimmatech.etl.process.domain.EmployeeEntity;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmployeeProcessor implements ItemProcessor<EmployeeEntity, EmployeeEntity> {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectMapper mapper;

	private String erroneousRecord = "ER";

	private String validRecord = "VR";
	@Autowired
	private IValidationClientService validationClientService;

	@Autowired
	Validator<EmployeeEntity> validator;
	@Autowired
	private BeanValidator<EmployeeEntity> beanValidator;

	@Override
	public EmployeeEntity process(EmployeeEntity employee) throws Exception {
		beanValidator.validate(employee);
		log.info("Transforming the data in processor ");
		employee.setIsProcessed(true);
		employee.setRecordStatus(validRecord);
		log.info("Validating the employee age ..... ");
		if (null != employee.getBirthDate()) {
			EmployeeDTO emp = convertToDto(employee);
			ResponseEntity<BussinessValidationDTO> bussinessValidationDTOResponse = validationClientService
					.validateEmployeeAge(emp);
			BussinessValidationDTO bussinessValidationDTO = bussinessValidationDTOResponse.getBody();
			if (bussinessValidationDTOResponse.getStatusCodeValue() == 200) {
				employee = bindValidationJsonWithEmployee(employee, bussinessValidationDTO);
				employee.setIsAgeValidated(true);
			} else {
				employee.setIsAgeValidated(false);
				employee.setEmployeeValidations(null);
			}
			log.info("The validations for the  - {} record {} ", employee.getFirstName(), bussinessValidationDTO);
		}
		return employee;
	}

	@OnProcessError
	void onProcessError(EmployeeEntity item, Exception e) {
		log.error("Exception occurred in input validation at the {} th item. [message:{}]", item.getCount(),
				e.getMessage());
	}

	@OnReadError
	public void houstonWeHaveAProblemOnRead(Exception e) {
		log.error("Exception occurred in input validation at the {} th item. [message:{}]");
	}

	@OnSkipInWrite
	public void houstonWeHaveAProblemOnWrite(EmployeeEntity item, Exception e) {
		log.error("Exception occurred in input validation at the {} th item. [message:{}]", item.getCount(),
				e.getMessage());
	}

	private EmployeeDTO convertToDto(EmployeeEntity employeeEntity) {
		return modelMapper.map(employeeEntity, EmployeeDTO.class);

	}

	private EmployeeEntity bindValidationJsonWithEmployee(EmployeeEntity employee,
			BussinessValidationDTO bussinessValidationDTO) {
		if (bussinessValidationDTO != null) {
			if (bussinessValidationDTO.getEmployeeValidationDTO() != null
					&& bussinessValidationDTO.getEmployeeValidationDTO().size() >= 1) {
				employee.setRecordStatus(erroneousRecord);
				String json;
				try {
					json = mapper.writeValueAsString(bussinessValidationDTO);
					JsonNode employeeValidations = mapper.readTree(json);
					System.out.println("The employee validations are " + employee);
					employee.setEmployeeValidations(employeeValidations);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			} else {
				employee.setEmployeeValidations(null);
			}
		}
		return employee;
	}
}
