package br.luciano.ItemWriter_batch.step;

import br.luciano.ItemWriter_batch.dominio.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ArquivoLarguraFixaStepConfig {
	@Autowired
	private PlatformTransactionManager transactionManager;

	@Bean
	public Step arquivoLarguraFixaStep(ItemReader<Cliente> leituraArquivoLarguraFixaReader,
									   ItemWriter<Cliente> arquivoLarguraFixaWriter, JobRepository jobRepository) {
		return new StepBuilder("arquivoLarguraFixaStep", jobRepository)
				.<Cliente, Cliente>chunk(1, transactionManager)
				.reader(leituraArquivoLarguraFixaReader)
				.writer(arquivoLarguraFixaWriter)
				.build();
	}
}
