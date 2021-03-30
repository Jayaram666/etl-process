package brimmatech.etl.process;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, StepScopeTestExecutionListener.class })
@SpringBootTest(classes = { ApplicationContextConfig.class })
public class BrimmatechEtlProcessApplicationTests {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void testETLDataBaseLoad() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
	}
	
}
