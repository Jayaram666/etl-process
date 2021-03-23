package brimmatech.etl.process.config;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import brimmatech.etl.process.domain.EmployeeEntity;


@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeEntity, EmployeeEntity> {

	Logger logger = LoggerFactory.getLogger(EmployeeProcessor.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value( "${validation.service.url}")
	private String validationServiceURL;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectMapper mapper;

	private String erroneousRecord = "ER";

	private String validRecord = "VR";


	@Override
	public EmployeeEntity process(EmployeeEntity employee) throws Exception {
		logger.info("Transforming the data in processor ");
		employee.setIsProcessed(true);
		employee.setRecordStatus(validRecord);

		if (null != employee.getBirthDate()) {
			EmployeeDTO emp = convertToDto(employee);
			HttpEntity<EmployeeDTO> request = new HttpEntity<>(emp);
			BussinessValidationDTO bussinessValidationDTO = restTemplate.postForObject(validationServiceURL, request,
					BussinessValidationDTO.class);

			if (bussinessValidationDTO != null) {
				if (bussinessValidationDTO.getEmployeeValidationDTO() != null
						&& bussinessValidationDTO.getEmployeeValidationDTO().size() >= 1) {
					employee.setRecordStatus(erroneousRecord);
				}
			}

			String json = mapper.writeValueAsString(bussinessValidationDTO);
			JsonNode employeeValidations = mapper.readTree(json);
			employee.setEmployeeValidations(employeeValidations);
			logger.info("The validations for the  - {} record {} ", employee.getFirstName(),
					bussinessValidationDTO.toString());
		}
		return employee;
	}

	private EmployeeDTO convertToDto(EmployeeEntity employeeEntity) {
		EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
		return employeeDTO;
	}

}
