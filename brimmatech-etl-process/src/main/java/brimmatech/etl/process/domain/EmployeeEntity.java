package brimmatech.etl.process.domain;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.JsonNode;

import brimmatech.etl.process.config.EmployeeValidationJsonNodeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "employee")
public class EmployeeEntity {

	@Id
	@GeneratedValue
	@Column( name = "employee_id")
	private Integer employeeId;

	@Column( name = "firstName")
	private String firstName;

	@Column( name = "last_name")
	private String lasstName;

	@Column( name = "email")
	private String email;

	@Column( name = "birth_date")
	private LocalDate birthDate;

	@Column( name = "country")
	private String country;

	@Column( name = "company")
	private String company;

	@Column( name = "is_processed")
	private Boolean isProcessed;

	@Column( name = "validations")
	@Convert( converter = EmployeeValidationJsonNodeConverter.class)
	private JsonNode employeeValidations;

	@Column( name = "record_status")
	private String recordStatus;


}
