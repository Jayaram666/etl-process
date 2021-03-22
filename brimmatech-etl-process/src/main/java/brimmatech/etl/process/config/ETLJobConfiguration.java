package brimmatech.etl.process.config;

import org.modelmapper.ModelMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import brimmatech.etl.process.domain.EmployeeEntity;

@Configuration
@Component
public class ETLJobConfiguration {
	
	@Bean
	public Job job(JobBuilderFactory jobbuilderFactory, StepBuilderFactory stepBuilderFactory,
			ItemReader<EmployeeEntity> itemreader, ItemWriter<EmployeeEntity> itemWriter,
			ItemProcessor<EmployeeEntity, EmployeeEntity> processor) {

		Step step = stepBuilderFactory.get("ETL_DB_LOAD")
				.<EmployeeEntity, EmployeeEntity>chunk(100)
				.reader(itemreader)
				.processor(processor)
				.writer(itemWriter)
				.build();

		Job job = jobbuilderFactory
				.get("ETL_Load")
				.incrementer(new RunIdIncrementer())
				.start(step)
				.build();
		
		return job;
	}

	

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplateBean() {
		return new RestTemplate();
	}

}
