package br.luciano.ItemWriter_batch.writer;

import br.luciano.ItemWriter_batch.dominio.GrupoLancamento;
import br.luciano.ItemWriter_batch.dominio.Lancamento;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.file.ResourceSuffixCreator;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class DemonstrativoOrcamentarioWriterConfig {

    @StepScope
    @Bean
    public MultiResourceItemWriter<GrupoLancamento> multiDemonstrativoOrcamentarioItemWriter(
            @Value("#{jobParameters['demonstrativosOrcamentarios']}") Resource demonstrativosOrcamentarios,
            FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter) {

        return new MultiResourceItemWriterBuilder<GrupoLancamento>()
                .name("multiDemonstrativoOrcamentarioItemWriter")
                .resource(demonstrativosOrcamentarios)
                .delegate(demonstrativoOrcamentarioWriter)
                .resourceSuffixCreator(suffixCreator())
                .itemCountLimitPerResource(1)
                .build();

    }

    private ResourceSuffixCreator suffixCreator() {
        return new ResourceSuffixCreator() {
            @Override
            public String getSuffix(int index) {
                return index + ".txt";
            }
        };
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<GrupoLancamento> demonstrativoOrcamentarioWriter(
            @Value("#{jobParameters['arquivoSaida']}") Resource arquivoSaida,
            DemonstrativoOrcamentarioRodape rodapeCallback) {

        return new FlatFileItemWriterBuilder<GrupoLancamento>()
                .name("demonstrativoOrcamentarioWriter")
                .resource((WritableResource) arquivoSaida)
                .lineAggregator(lineAggregator())
                .headerCallback(cabecalhoCallback())
                .footerCallback(rodapeCallback)
                .build();
    }

    private FlatFileHeaderCallback cabecalhoCallback() {
        return new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.append(String.format("SISTEMA INTEGRADO: XYZ  \t\t\t\t DATA: %s%n", new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
                writer.append(String.format("MÓDULO: ORÇAMENTO \t\t\t\t\t\t HORA: %s%n", new SimpleDateFormat("HH:MM").format(new Date())));
                writer.append(String.format("\t\t\tDEMONSTRATIVO ORCAMENTARIO%n"));
                writer.append(String.format("----------------------------------------------------------------------------%n"));
                writer.append(String.format("CODIGO NOME VALOR%n"));
                writer.append(String.format("\t Data Descricao Valor%n"));
                writer.append(String.format("----------------------------------------------------------------------------%n"));
            }
        };
    }

    private LineAggregator<GrupoLancamento> lineAggregator() {
        return new LineAggregator<GrupoLancamento>() {
            @Override
            public String aggregate(GrupoLancamento grupoLancamento) {
                String formatoGrupoLancamento = String.format("[%d] %s - %s%n", grupoLancamento.getCodigoNaturezaDespesa(),
                        grupoLancamento.getDescricaoNaturezaDespesa(),
                        NumberFormat.getCurrencyInstance().format(grupoLancamento.getTotal()));
                StringBuilder strBuilder = new StringBuilder();

                for (Lancamento lancamento : grupoLancamento.getLancamentos()) {
                    strBuilder.append(String.format("\t [%s] %s - %s%n", new SimpleDateFormat("dd/MM/yyyy").format(lancamento.getData()), lancamento.getDescricao(),
                            NumberFormat.getCurrencyInstance().format(lancamento.getValor())));
                }

                String formatLancamento = strBuilder.toString();
                return formatoGrupoLancamento + formatLancamento;
            }
        };
    }
}
