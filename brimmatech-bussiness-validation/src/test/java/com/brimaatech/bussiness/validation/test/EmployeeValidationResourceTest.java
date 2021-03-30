package com.brimaatech.bussiness.validation.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.brimmatech.dto.EmployeeDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeValidationResourceTest.class)
public class EmployeeValidationResourceTest {

	@Test
	public void validateAgeTest() throws URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:8081/validations/employee-bussiness-validation";
		URI uri = new URI(baseUrl);

		EmployeeDTO employee = new EmployeeDTO(12, "Adam", "Gilly", "test@email.com", LocalDate.of(2009, 02, 02), null,
				null);
		

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<EmployeeDTO> request = new HttpEntity<>(employee, headers);
		try {
			ResponseEntity<Object> result = restTemplate.postForEntity(uri, request, Object.class); //
			Assert.assertEquals(200, result.getStatusCodeValue());
		} catch (HttpClientErrorException ex) {
		}

	}

}
