package brimmatech.etl.process.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class ValidationClientService implements IValidationClientService {

	@Autowired
	private RestTemplate restTemplate;

	@Value( "${validation.service.url}")
	private String validationServiceURL;


	@Override
	@HystrixCommand( fallbackMethod = "validateEmployeeAgeFallBack")
	public BussinessValidationDTO validateEmployeeAge(EmployeeDTO employee) {
		HttpEntity<EmployeeDTO> request = new HttpEntity<>(employee);
		return restTemplate.postForObject(validationServiceURL, request, BussinessValidationDTO.class);
	}

	public BussinessValidationDTO validateEmployeeAgeFallBack(EmployeeDTO employee) {
		return new BussinessValidationDTO();
	}

}
