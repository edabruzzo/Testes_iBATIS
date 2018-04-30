/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import com.ibatis.sqlmap.client.SqlMapClient;
import java.io.IOException;
import util.FabricaConexao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author internet
 */
public class Operacoes_Banco {

    private Funcionario funcionario = new Funcionario();
    float salarioComDescontoINSS;
    String bancoUtilizado = "PostgreSQL";

    public void consultaFuncionarios() throws ClassNotFoundException, IOException, SQLException {

        //Não estou conseguindo instanciar a conexão como campo da classe 
        //por conta de tratamento da exceção IOException
        FabricaConexao novaConexao = new FabricaConexao(bancoUtilizado);
        SqlMapClient smc = novaConexao.getSmc();

        List<Funcionario> lista = (List<Funcionario>) smc.queryForList("Funcionario.getAll", null);
        Funcionario funcionarioLido = null;

        System.out.println("LISTANDO FUNCIONÁRIOS !!!");

        for (Funcionario funcionario : lista) {
            System.out.print("  " + funcionario.getId());
            System.out.print("  " + funcionario.getNome());
            System.out.print("  " + funcionario.getCargo());
            System.out.print("  " + funcionario.getNumeroDependentes());
            System.out.print("  " + funcionario.getSalario());
            funcionarioLido = funcionario;
            System.out.println(funcionarioLido.toString());
        }

        System.out.println("FUNCIONÁRIOS LIDOS COM SUCESSO !");
    }

    public void buscarID(Integer idFuncionario) throws IOException {

        FabricaConexao novaConexao = new FabricaConexao(bancoUtilizado);
        SqlMapClient smc = novaConexao.getSmc();

    }

    public void inserir(String nome, String cargo, int numeroDependentes, float salario) throws ClassNotFoundException, SQLException, ParseException, IOException {

        funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setNumeroDependentes(numeroDependentes);
        funcionario.setSalario(salario);

        //Não estou conseguindo instanciar a conexão como campo da classe 
        //por conta de tratamento da exceção IOException
        FabricaConexao novaConexao = new FabricaConexao(bancoUtilizado);
        SqlMapClient smc = novaConexao.getSmc();

        smc.insert("Funcionario.insert", funcionario);

        System.out.println("Funcionario inserido com sucesso !");
    }

    public void atualizar(int id, float salario) throws ClassNotFoundException {

        System.out.println("ATUALIZANDO FUNCIONÁRIOS !!!");

    }

    public void remover(int idFuncionario) throws ClassNotFoundException {

        System.out.println("DELETANDO FUNCIONÁRIOS !!!");

    }

    public void gerarFolhaPagamento() throws ClassNotFoundException, SQLException, IOException {

        //Não estou conseguindo instanciar a conexão como campo da classe 
        //por conta de tratamento da exceção IOException
        FabricaConexao novaConexao = new FabricaConexao(bancoUtilizado);
        SqlMapClient smc = novaConexao.getSmc();

        List<Funcionario> lista = (List<Funcionario>) smc.queryForList("Funcionario.getAll", null);

        System.out.println("*************** FIM DA FOLHA DE PAGAMENTO FUNCIONÁRIOS ***************************");

        for (Funcionario funcionario : lista) {

            System.out.print("  " + funcionario.getId());
            System.out.print("  " + funcionario.getNome());
            System.out.print("  " + funcionario.getCargo());
            System.out.print("  " + funcionario.getNumeroDependentes());
            System.out.print("  " + funcionario.getSalario());

            //Display values
            System.out.println("ID DO FUNCIONARIO: " + funcionario.getId());
            System.out.println(", NOME: " + funcionario.getNome());
            System.out.println(", CARGO: " + funcionario.getCargo());
            System.out.println(", NÚMERO DE DEPENDENTES: " + funcionario.getNumeroDependentes());
            System.out.println(", SALÁRIO BRUTO: R$" + funcionario.getSalario());
            System.out.println(", SALÁRIO COM DESCONTO DO INSS: R$"
                    + funcionario.getSalarioComDescontoINSS() + "\n\n");

        }
        System.out.println("*************** FIM DA FOLHA DE PAGAMENTO FUNCIONÁRIOS ***************************");

    }

    //AINDA NÃO SEI COMO CRIAR TABELA
    void criarTabelaMySQL() throws ClassNotFoundException, IOException {

        FabricaConexao novaConexao = new FabricaConexao(bancoUtilizado);
        SqlMapClient smc = novaConexao.getSmc();

        System.out.println("CRIANDO TABELA funcionario !!!");
       
        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            Statement stmt = conn.createStatement();
            String sql;
            sql = ("CREATE TABLE IF NOT EXISTS funcionario\n"
                    + "              (id INT NOT NULL AUTO_INCREMENT,\n"
                    + "              nome VARCHAR(255),\n"
                    + "              cargo VARCHAR(255),\n"
                    + "              numeroDependentes INT,\n"
                    + "              Salario FLOAT,\n"
                    + "              PRIMARY KEY (id));");

            stmt.executeUpdate(sql);
            String warning = stmt.getWarnings().getMessage();
            System.out.println("***************TABELA CRIADA COM SUCESSO ***************************");
            System.out.println("CONSEGUI PEGAR O WARNING DO BANCO : " + warning);
            stmt.close();
            novaConexao.fecharConexao(conn);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            if (conn != null) {
                novaConexao.fecharConexao(conn);
            } //end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public void criarFuncao() throws ClassNotFoundException, SQLException, IOException {

        FabricaConexao novaConexao = new FabricaConexao(bancoUtilizado);
        SqlMapClient smc = novaConexao.getSmc();

        
        if (novaConexao.getBancoDadosEscolhido() == "MySQL") {
            criaFuncaoTesteMYSQL();
        } else {
            criaFuncaoTestePostgreSQL();
        }

    }

    void criaFuncaoTesteMYSQL() throws ClassNotFoundException, SQLException {

        FabricaConexao novaConexao = new FabricaConexao(bancoUtilizado);
        SqlMapClient smc = novaConexao.getSmc();

        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            Statement stmt = conn.createStatement();
            stmt.execute("DROP PROCEDURE IF EXISTS hello;");
            stmt = conn.createStatement();
            String sql;
            sql = this.leitorArquivos.lerArquivoConsulta("criaFuncaoTesteMySQL");
            stmt.execute(sql);

            //     String warning = stmt.getWarnings().getMessage();
            System.out.println("***************FUNÇÃO CRIADA COM SUCESSO ***************************");
            //   System.out.println("CONSEGUI PEGAR O WARNING DO BANCO : " + warning);
            stmt.close();
            novaConexao.fecharConexao(conn);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            if (conn != null) {
                novaConexao.fecharConexao(conn);
            } //end finally try

        }//end try
        System.out.println("Goodbye!");

    }

    public void chamandoFuncao(String pessoaPesquisada) throws ClassNotFoundException, SQLException, IOException {

        FabricaConexao novaConexao = new FabricaConexao(bancoUtilizado);
        SqlMapClient smc = novaConexao.getSmc();

        if (novaConexao.getBancoDadosEscolhido() == "MySQL") {

            chamandoFuncaoMySql(pessoaPesquisada);

        } else if (novaConexao.getBancoDadosEscolhido() == "PostgreSQL") {

            chamandoFuncaoPostgreSQL(pessoaPesquisada);

        }

    }

    public void chamandoFuncaoMySql(String pessoaPesquisada) throws ClassNotFoundException, SQLException {

        FabricaConexao novaConexao = new FabricaConexao(bancoUtilizado);
        SqlMapClient smc = novaConexao.getSmc();

        System.out.println("CHAMANDO FUNÇÃO PARA A SEGUINTE PESSOA PESQUISADA:"
                + " " + pessoaPesquisada + " ! ");

        Connection conn = novaConexao.criaConexao();
        System.out.println("Creating statement...");

        CallableStatement callableStatement = null;
        callableStatement = conn.prepareCall("{call hello(?, ?)}");

        callableStatement.setString("nomePessoa", pessoaPesquisada);
        callableStatement.registerOutParameter("resposta", Types.VARCHAR);

        //Use execute method to run stored procedure.
        System.out.println("Executing stored procedure ...");
        try {
            callableStatement.execute();
            System.out.println("***************FUNÇÃO EXECUTADA COM SUCESSO ***************************");
            //      String warning = callableStatement.getWarnings().getMessage();
            //    System.out.println("CONSEGUI PEGAR O WARNING DA FUNÇÃO FORA DO CATCH : " + warning);
            String resultadoFuncao = callableStatement.getString(2);
            System.out.println("RESULTADO DA FUNÇÃO: " + resultadoFuncao);
            this.novaConexao.fecharConexao(conn);
            callableStatement.close();

        } catch (SQLException se) {
            String warning = se.getMessage();
            System.out.println("CONSEGUI PEGAR O WARNING DA FUNÇÃO DENTRO DO CATCH : " + warning);
        } finally {
            if (conn != null) {

                novaConexao.fecharConexao(conn);

            }

        }

    }

    public String chamandoFuncaoPostgreSQL(String pessoaPesquisada) throws ClassNotFoundException, SQLException {

        String solucao = null;

        
        System.out.println("CHAMANDO FUNÇÃO ***** COM CALLABLESTATEMENT ********");
        System.out.println( "PARA A SEGUINTE PESSOA PESQUISADA: " + pessoaPesquisada);

        Connection conn = novaConexao.criaConexao();
        System.out.println("Creating statement...");

        CallableStatement callableStatement = null;
        callableStatement = conn.prepareCall("{? = call hello(?)}");

        callableStatement.setString(2, pessoaPesquisada);
        callableStatement.registerOutParameter(1, Types.VARCHAR);

        //Use execute method to run stored procedure.
        System.out.println("Executing stored procedure ...");
        try {
            callableStatement.execute();
            System.out.println("***************FUNÇÃO EXECUTADA COM SUCESSO ***************************");
            //      String warning = callableStatement.getWarnings().getMessage();
            //    System.out.println("CONSEGUI PEGAR O WARNING DA FUNÇÃO FORA DO CATCH : " + warning);
            String resultadoFuncao = callableStatement.getString(2);
            System.out.println("RESULTADO DA FUNÇÃO: " + resultadoFuncao);
            this.novaConexao.fecharConexao(conn);
            callableStatement.close();

        } catch (SQLException se) {
           
            String  warning = callableStatement.getWarnings().getMessage();
            solucao = warning;
            System.out.println("CONSEGUI PEGAR o WARNING E NÃO A EXCEPTION DENTRO DO CATCH : ");
            System.out.println("*********************************************************");
            System.out.println(warning);
            System.out.println("*********************************************************");
           
        } finally {
            if (conn != null) {

                novaConexao.fecharConexao(conn);

            }

        }

        return solucao;
    }

    private void criaFuncaoTestePostgreSQL() throws ClassNotFoundException {

        System.out.println("CRIANDO FUNÇÃO hello() NO BANCO POSTGRESQL!!!");
        Connection conn = novaConexao.criaConexao();

        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");

            Statement stmt = conn.createStatement();
            stmt = conn.createStatement();
            String sql = leitorArquivos.lerArquivoConsulta("Teste");
            stmt.execute(sql);

            //     String warning = stmt.getWarnings().getMessage();
            System.out.println("***************FUNÇÃO CRIADA COM SUCESSO ***************************");
            //   System.out.println("CONSEGUI PEGAR O WARNING DO BANCO : " + warning);
            stmt.close();
            novaConexao.fecharConexao(conn);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            if (conn != null) {
                novaConexao.fecharConexao(conn);
            } //end finally try

        }//end try
        System.out.println("Goodbye!");

    }

    private void criarBancoPostgreSQL() throws ClassNotFoundException {

        System.out.println("NÃO É NECESSÁRIA A CRIAÇÃO DO BANCO PELA APLICAÇÃO."
                + "CASO O BANCO NÃO SEJA CRIADO, VOCÊ DEVERÁ CRIAR O BANCO NO CONSOLE DO PGADMIN III"
                + "EM QUALQUER  BANCO JÁ  EXISTENTE NO PostgreSQL!!!");

        System.out.println("DIGITE NO CONSOLE DO PGADMIN O SEGUINTE COMANDO :");

        System.out.println("CREATE EXTENSION IF NOT EXISTS dblink;\n"
                + "\n"
                + "DO\n"
                + "$do$\n"
                + "BEGIN\n"
                + "   IF EXISTS (SELECT 1 FROM pg_database WHERE datname = 'empresa') THEN\n"
                + "      RAISE NOTICE 'Database already exists'; \n"
                + "   ELSE\n"
                + "      PERFORM  dblink_exec('dbname=' || current_database()-- current db\n"
                + "                        , 'CREATE DATABASE empresa', false);\n"
                + "   END IF;\n"
                + "END\n"
                + "$do$;\n"
                + "\n"
                + "DROP EXTENSION dblink;");

        /*  try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            this.novaConexao.setURL("jdbc:mysql://localhost:5433/");
            Statement stmt = novaConexao.criaConexao().createStatement();
            String sql;
            sql = ("CREATE DATABASE empresa");
            System.out.println(sql);
            stmt.executeUpdate(sql);
            String warning = stmt.getWarnings().getMessage();
            
            System.out.println("***************BANCO CRIADO COM SUCESSO ***************************");
            System.out.println("CONSEGUI PEGAR O WARNING DO BANCO : " + warning);
            stmt.close();
            novaConexao.criaConexao().close();
            this.novaConexao = new FabricaConexao("PostgreSQL");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (novaConexao.criaConexao() != null) {
                    novaConexao.criaConexao().close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

         */
    }

    public void criarTabela() throws ClassNotFoundException {
        if (this.novaConexao.getBancoDadosEscolhido() == "MySQL") {

            criarTabelaMySQL();

        } else if (this.novaConexao.getBancoDadosEscolhido() == "PostgreSQL") {

            criarTabelaPostgreSQL();
        }

    }

    private void criarTabelaPostgreSQL() throws ClassNotFoundException {

        System.out.println("CRIANDO TABELA funcionario !!!");
        Connection conn = this.novaConexao.criaConexao();

        try {
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            Statement stmt = conn.createStatement();
            String sql;
            sql = ("CREATE TABLE IF NOT EXISTS funcionario\n"
                    //https://chartio.com/resources/tutorials/how-to-define-an-auto-increment-primary-key-in-postgresql/
                    + "              (id SERIAL PRIMARY KEY,\n"
                    + "              nome VARCHAR(255),\n"
                    + "              cargo VARCHAR(255),\n"
                    + "              numeroDependentes INT,\n"
                    + "              Salario FLOAT);");

            System.out.println(sql);

            stmt.executeUpdate(sql);
            String warning = stmt.getWarnings().getMessage();
            System.out.println("***************TABELA CRIADA COM SUCESSO ***************************");
            System.out.println("CONSEGUI PEGAR O WARNING DO BANCO : " + warning);
            stmt.close();
            novaConexao.fecharConexao(conn);
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (conn != null) {
                    novaConexao.criaConexao().close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }

    public void criaFuncaoTeste() throws ClassNotFoundException, SQLException {

        if (this.novaConexao.getBancoDadosEscolhido() == "MySQL") {

            criaFuncaoTesteMYSQL();

        } else if (this.novaConexao.getBancoDadosEscolhido() == "PostgreSQL") {

            criaFuncaoTestePostgreSQL();
        }

    }

}
