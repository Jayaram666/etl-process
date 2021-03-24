package brimmatech.etl.process;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;


@SpringBootApplication
@EnableBatchProcessing
@EnableCircuitBreaker

public class BrimmatechEtlProcessApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrimmatechEtlProcessApplication.class, args);
	}


}
