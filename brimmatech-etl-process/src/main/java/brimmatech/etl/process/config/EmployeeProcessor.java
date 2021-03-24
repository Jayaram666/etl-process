package brimmatech.etl.process.config;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import brimmatech.etl.process.client.IValidationClientService;
import brimmatech.etl.process.domain.EmployeeEntity;


@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeEntity, EmployeeEntity> {

	Logger logger = LoggerFactory.getLogger(EmployeeProcessor.class);


	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectMapper mapper;

	private String erroneousRecord = "ER";

	private String validRecord = "VR";
	@Autowired
	private IValidationClientService validationClientService;


	@Override
	public EmployeeEntity process(EmployeeEntity employee) throws Exception {
		logger.info("Transforming the data in processor ");
		employee.setIsProcessed(true);
		employee.setRecordStatus(validRecord);

		if (null != employee.getBirthDate()) {
			EmployeeDTO emp = convertToDto(employee);
			BussinessValidationDTO bussinessValidationDTO = validationClientService.validateEmployeeAge(emp);
			employee = bindValidationJsonWithEmployee(employee, bussinessValidationDTO);
			logger.info("The validations for the  - {} record {} ", employee.getFirstName(),
					bussinessValidationDTO.toString());
		}
		return employee;
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
					employee.setEmployeeValidations(employeeValidations);
				}
				catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
		}
		return employee;
	}
}
