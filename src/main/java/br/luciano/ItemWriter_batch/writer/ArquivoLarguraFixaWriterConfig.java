package br.luciano.ItemWriter_batch.writer;

import br.luciano.ItemWriter_batch.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
public class ArquivoLarguraFixaWriterConfig {

	@StepScope
	@Bean
	public FlatFileItemWriter<Cliente> arquivoLarguraFixaWriter(
			@Value("#{jobParameters['arquivoSaida']}") Resource arquivoSaida) {
		return new FlatFileItemWriterBuilder<Cliente>()
				.name("arquivoLarguraFixaWriter")
				.resource((WritableResource) arquivoSaida)
				.formatted()
				.format("%-9s %-9s %-2s %-19s")
				.names("nome", "sobrenome", "idade", "email")
				.build();
	}
}
