package brimmatech.etl.process.config;


import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import brimmatech.etl.process.domain.EmployeeEntity;
import brimmatech.etl.process.repository.EmployeeRepository;


@Configuration
public class EmployeeDBWriter implements ItemWriter<EmployeeEntity> {

	@Autowired
	private EmployeeRepository userRepository;


	@Override
	public void write(List<? extends EmployeeEntity> items) throws Exception {
		userRepository.saveAll(items);
	}

}
