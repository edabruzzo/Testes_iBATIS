# Testes_iBATIS

ALTERAÇÕES NO PROJETO TESTS_JDBC PARA UTILIZAÇÃO DO iBATIS:

Testando a utilização do iBATIS em substituição ao JDBC puro.

Utilizando o DataSources do iBATIS (SqlMapConfig) para duas conexões em bancos diferentes (MySQL e PostgreSQL)

Permitindo a escolha do banco como parâmetro do construtor da classe util.FabricaConexao.

INÍCIO DAS ALTERAÇÕES NO PROJETO TESTS_JDBC

Inclusao dos data sources do iBatis na raiz do projeto; alterações no modo de produzir as conexões e exclusão do LeitorArquivos para as consultas SQL e exclusão dos arquivos de Consulta SQL.
