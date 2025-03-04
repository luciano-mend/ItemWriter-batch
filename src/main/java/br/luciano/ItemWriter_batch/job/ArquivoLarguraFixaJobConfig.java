package br.luciano.ItemWriter_batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoLarguraFixaJobConfig {

	@Bean
	public Job arquivoLarguraFixaJob(@Qualifier("demonstrativoOrcamentarioStep") Step arquivoLarguraFixaStep,
									 JobRepository jobRepository) {
		return new JobBuilder("arquivoJob", jobRepository)
				.start(arquivoLarguraFixaStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
