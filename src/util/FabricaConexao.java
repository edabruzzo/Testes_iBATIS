package util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author internet
 */
public class FabricaConexao {

    private String JDBC_DRIVER;
    private String URL;
    private String USER;
    private String PASSWORD = "9922563e";
    private String bancoDadosEscolhido;
    
    

    public FabricaConexao(String bancoDados) {

        if ("MySQL".equals(bancoDados)) {

            this.bancoDadosEscolhido = bancoDados;
            this.JDBC_DRIVER = "com.mysql.jdbc.Driver";
            this.URL = "jdbc:mysql://localhost:3306/empresa";
            this.USER = "root";
            
        
        } else if ("PostgreSQL".equals(bancoDados)) {
        //https://www.postgresql.org/docs/7.4/static/jdbc-use.html
            this.bancoDadosEscolhido = bancoDados;
            this.JDBC_DRIVER = "org.postgresql.Driver";
            //ATENÇÃO A PORTA PADRÃO DO POSTGRESQL É A 5432
            this.URL = "jdbc:postgresql://localhost:5432/empresa";
            this.USER = "postgres";
            

        }
    
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
    
    
    public Connection criaConexao() throws ClassNotFoundException  {

        Class.forName(JDBC_DRIVER);
        Connection conn = null;
        //STEP 3: Open a connection
        System.out.println("Conectando ao servidor com a seguinte URL : "+ this.URL);
       
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;

    }

    public String getBancoDadosEscolhido() {
        return bancoDadosEscolhido;
    }

    public void fecharConexao(Connection conn) {
        
        System.out.println("Fechando a conexão com o banco de dados");
        try {
            conn.close();
           System.out.println("Conexão com o banco de dados fechada com sucesso");

        } catch (SQLException ex) {
            Logger.getLogger(FabricaConexao.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Conexão com o banco de dados NÃO FOI fechada !");


        }
    }

}
