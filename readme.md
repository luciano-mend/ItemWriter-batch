## Projeto utilizando Spring Batch

### Para executar é necessário ter:
- Banco PostgreSQL com 2 databases: spring_batch e app
- Java 17

As variáveis de ambiente:
```bash
DB_USER=nome_banco_dados
DB_PASSWORD=senha_banco_dados
```
O argumento do local do arquivo:
```bash
arquivoClientes=file:files/clientes.txt
```

#### Implementações
O projeto contém a utilização de tipos de ItemWriter.