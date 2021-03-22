package brimmatech.etl.process.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import brimmatech.etl.process.domain.EmployeeEntity;

@Configuration
public class EmployeeDBReader {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Value("${chumk.size}")
	private Integer chunkSize;
	
	@Bean
	public JpaPagingItemReader<EmployeeEntity> itemReader() {
		return new JpaPagingItemReaderBuilder<EmployeeEntity>()
				.name("employee_reader")
				.entityManagerFactory(entityManagerFactory)
				.queryString("select e from EmployeeEntity e")
				.pageSize(chunkSize)
				.build();
	}
}
