package br.luciano.ItemWriter_batch.writer;

import br.luciano.ItemWriter_batch.dominio.GrupoLancamento;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;

@Component
public class DemonstrativoOrcamentarioRodape implements FlatFileFooterCallback {
    private Double totalGeral = 0.0;

    @Override
    public void writeFooter(Writer writer) throws IOException {
        writer.append("\n");
        writer.append(String.format("\t\t\t\t\t\t\t  Total: %s%n", NumberFormat.getCurrencyInstance().format(totalGeral)));
        writer.append(String.format("\t\t\t\t\t\t\t  Código de Autenticação: %s%n", "fkyew6868fewjfhjjewf"));
    }

    @BeforeWrite
    public void beforeWrite(Chunk<GrupoLancamento> grupos) {
        for (GrupoLancamento grupo : grupos) {
            totalGeral += grupo.getTotal();
        }
    }
}
