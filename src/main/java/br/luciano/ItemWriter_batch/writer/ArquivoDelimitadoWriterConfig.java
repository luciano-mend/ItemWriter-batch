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
public class ArquivoDelimitadoWriterConfig {
	@StepScope
	@Bean
	public FlatFileItemWriter<Cliente> arquivoDelimitadoWriter(
			@Value("#{jobParameters['arquivoSaida']}") Resource arquivoSaida) {

		return new FlatFileItemWriterBuilder<Cliente>()
				.name("arquivoDelimitadoWriter")
				.resource((WritableResource) arquivoSaida)
				.delimited()
				.delimiter(";")
				.names("nome", "sobrenome", "idade", "email")
				.build();
	}
}
