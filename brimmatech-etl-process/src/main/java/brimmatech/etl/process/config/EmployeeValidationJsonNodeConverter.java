package brimmatech.etl.process.config;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Converter
public class EmployeeValidationJsonNodeConverter implements AttributeConverter<JsonNode, String> {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeValidationJsonNodeConverter.class);
	@Autowired
	private ObjectMapper mapper;


	@Override
	public String convertToDatabaseColumn(JsonNode jsonNode) {
		if (jsonNode == null) {
			logger.warn("jsonNode input is null, returning null");
			return null;
		}

		String jsonNodeString = jsonNode.toPrettyString();
		return jsonNodeString;
	}


	@Override
	public JsonNode convertToEntityAttribute(String jsonNodeString) {


		if (null == jsonNodeString) {
			logger.warn("jsonNodeString input is empty, returning null");
			return null;
		}

		try {
			return mapper.readTree(jsonNodeString);
		}
		catch (JsonProcessingException e) {
			logger.error("Error parsing jsonNodeString", e);
		}
		catch (Exception e) {
			logger.error("Error parsing jsonNodeString", e);
		}
		return null;
	}

}
