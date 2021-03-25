package brimmatech.etl.process.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.batch.item.ItemCountAware;

import com.fasterxml.jackson.databind.JsonNode;

import brimmatech.etl.process.config.EmployeeValidationJsonNodeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class EmployeeEntity implements ItemCountAware {

	private int count;
	@Id
	@GeneratedValue
	@Column(name = "employee_id")
	private Integer employeeId;

	@Column(name = "firstName")
	@Size(max = 15, message = "first name size is exceeded")
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message = "The above field must not be blank.")
	private String lasstName;

	@Column(name = "email")
	private String email;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "country")
	private String country;

	@Column(name = "company")
	private String company;

	@Column(name = "is_processed")
	private Boolean isProcessed;

	@Column(name = "is_age_validated")
	private Boolean isAgeValidated;

	@Column(name = "validations")
	@Convert(converter = EmployeeValidationJsonNodeConverter.class)
	private JsonNode employeeValidations;

	@Column(name = "record_status")
	private String recordStatus;

	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Override
	public void setItemCount(int count) {
		this.count = count;
	}
}
