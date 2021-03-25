package brimmatech.etl.process.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brimmatech.dto.BussinessValidationDTO;
import com.brimmatech.dto.EmployeeDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidationClientService implements IValidationClientService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${validation.service.url}")
	private String validationServiceURL;

	@Override
	@HystrixCommand(fallbackMethod = "validateEmployeeAgeFallBack")
	public ResponseEntity<BussinessValidationDTO> validateEmployeeAge(EmployeeDTO employee) {
		HttpEntity<EmployeeDTO> request = new HttpEntity<>(employee);
		ResponseEntity<BussinessValidationDTO> response = restTemplate.exchange(validationServiceURL, HttpMethod.POST,
				request, BussinessValidationDTO.class);
		log.info("The validation rest api has invoked successfully");
		return response;
	}

	public ResponseEntity<BussinessValidationDTO> validateEmployeeAgeFallBack(EmployeeDTO employee) {
		log.info("The validation rest api response from fallBack method");
		return new ResponseEntity<BussinessValidationDTO>(HttpStatus.valueOf(500));
	}

}
