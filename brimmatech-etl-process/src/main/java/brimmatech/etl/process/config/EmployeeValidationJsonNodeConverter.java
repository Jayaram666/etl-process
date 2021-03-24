package brimmatech.etl.process.config;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Converter
@Slf4j
public class EmployeeValidationJsonNodeConverter implements AttributeConverter<JsonNode, String> {

	@Autowired
	private ObjectMapper mapper;


	@Override
	public String convertToDatabaseColumn(JsonNode jsonNode) {
		if (jsonNode == null) {
			log.warn("jsonNode input is null, returning null");
			return null;
		}

		String jsonNodeString = jsonNode.toPrettyString();
		return jsonNodeString;
	}


	@Override
	public JsonNode convertToEntityAttribute(String jsonNodeString) {


		if (null == jsonNodeString) {
			log.warn("jsonNodeString input is empty, returning null");
			return null;
		}

		try {
			return mapper.readTree(jsonNodeString);
		}
		catch (JsonProcessingException e) {
			log.error("Error parsing jsonNodeString", e);
		}
		catch (Exception e) {
			log.error("Error parsing jsonNodeString", e);
		}
		return null;
	}

}
