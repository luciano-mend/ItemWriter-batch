## Projeto utilizando Spring Batch

### Para executar é necessário ter:
- Banco PostgreSQL com 2 databases: spring_batch e app
- Java 17
- Resources/scripts.sql aplicado no banco app

As variáveis de ambiente:
```bash
DB_USER=nome_banco_dados
DB_PASSWORD=senha_banco_dados
```
Os argumentos dos locais dos arquivos de leitura e escrita:
```bash
arquivoClientes=file:files/clientes.txt
arquivoSaida=file:files/clientesSaida.txt

#delimited
arquivoClientes=file:files/clientesDelimitado.txt
arquivoSaida=file:files/clientesSaidaDelimitado.txt

#MultiResourceItemReader
arquivosLancamento=file:files/lancamento*

#lineAggregator
arquivoSaida=file:files/demonstrativoSaida.txt
```

#### Implementações
O projeto contém a utilização de tipos de ItemWriter.
- FlatFileItemWriter
- FlatFileItemWriter com delimited
- FlatFileItemWriter com lineAggregator

#### Utilizado também:
- MultiResourceItemReader
- JdbcCursorItemReader