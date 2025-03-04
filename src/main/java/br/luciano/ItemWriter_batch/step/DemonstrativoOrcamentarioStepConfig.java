package br.luciano.ItemWriter_batch.step;

import br.luciano.ItemWriter_batch.dominio.GrupoLancamento;
import br.luciano.ItemWriter_batch.reader.GrupoLancamentoReader;
import br.luciano.ItemWriter_batch.writer.DemonstrativoOrcamentarioRodape;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DemonstrativoOrcamentarioStepConfig {
    @Autowired
    public PlatformTransactionManager transactionManager;

    @Bean
    public Step demonstrativoOrcamentarioStep(
            GrupoLancamentoReader demonstrativoOrcamentarioReader,
            @Qualifier("demonstrativoOrcamentarioWriter") ItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter,
            DemonstrativoOrcamentarioRodape rodapeCallback,
            JobRepository jobRepository) {

        return new StepBuilder("demonstrativoOrcamentarioStep", jobRepository)
                .<GrupoLancamento,GrupoLancamento>chunk(100, transactionManager)
                .reader(demonstrativoOrcamentarioReader)
                .writer(demonstrativoOrcamentarioWriter)
                .listener(rodapeCallback)
                .build();
    }
}
