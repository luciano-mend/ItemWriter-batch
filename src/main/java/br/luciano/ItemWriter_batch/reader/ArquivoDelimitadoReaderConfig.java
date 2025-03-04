package br.luciano.ItemWriter_batch.reader;

import br.luciano.ItemWriter_batch.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ArquivoDelimitadoReaderConfig {
	@StepScope
	@Bean
	public FlatFileItemReader<Cliente> leituraArquivoDelimitadoReader(
	@Value("#{jobParameters['arquivoClientes']}") Resource arquivoClientes) {
		return new FlatFileItemReaderBuilder<Cliente>()
                .name("leituraArquivoDelimitadoReader")
                .resource(arquivoClientes)
                .delimited()
                .names("nome", "sobrenome", "idade", "email")
                .targetType(Cliente.class)
                .build();
    }
}
