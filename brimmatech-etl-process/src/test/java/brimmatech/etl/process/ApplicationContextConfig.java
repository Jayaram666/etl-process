package brimmatech.etl.process;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ BrimmatechEtlProcessApplication.class })
@ComponentScan({ "brimmatech.etl.process", "brimmatech.etl.process.config" })
public class ApplicationContextConfig {
	@Autowired
	private Job etlDataBaseLoad;

	@Bean
	JobLauncherTestUtils jobLauncherTestUtils() throws NoSuchJobException {
		JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
		jobLauncherTestUtils.setJob(etlDataBaseLoad);

		return jobLauncherTestUtils;
	}
}