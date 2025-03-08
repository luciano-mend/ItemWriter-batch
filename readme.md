## Projeto utilizando Spring Batch
O projeto lê os dados (arquivo ou banco) e escreve um arquivo de saída, sendo ele formatado, delimitado, customizado ou customizado com cabeçalho e rodapé. Em seu formato final, lê o banco de dados, insere cabeçalho e rodapé, gera vários arquivos de acordo com o grupo de despesas, com o totalizador por arquivo.

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
#FlatFileItemWriter
arquivoClientes=file:files/clientes.txt
arquivoSaida=file:files/clientesSaida.txt

#delimited
arquivoClientes=file:files/clientesDelimitado.txt
arquivoSaida=file:files/clientesSaidaDelimitado.txt

#MultiResourceItemReader
arquivosLancamento=file:files/lancamento*

#lineAggregator
arquivoSaida=file:files/demonstrativoSaida.txt

#MultiResourceItemWriter
demonstrativosOrcamentarios=file:files/demonstrativosOrcamentariosSaida
```

#### Implementações
O projeto contém a utilização de tipos de ItemWriter.
- FlatFileItemWriter
- FlatFileItemWriter com delimited
- FlatFileItemWriter com lineAggregator
- FlatFileItemWriter com headerCallback, footerCallback e BeforeWrite
- MultiResourceItemWriter

#### Utilizado também:
- MultiResourceItemReader - leitura de vários arquivos txt.
- JdbcCursorItemReader - leitura do banco de dados.