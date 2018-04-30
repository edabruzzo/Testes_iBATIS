package util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author internet
 */
public class FabricaConexao {

    
    private Reader rd = null;      
    private SqlMapClient smc = null;
    private String bancoDadosEscolhido;
    
    

    public FabricaConexao(String bancoDados) throws IOException {
        
         this.bancoDadosEscolhido = bancoDados;
     

        if ("MySQL".equals(bancoDados)) {

      Reader rd = Resources.getResourceAsReader("SqlMapConfigMySQL.xml");      
      SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
            
        
        } else if ("PostgreSQL".equals(bancoDados)) {
       
      Reader rd = Resources.getResourceAsReader("SqlMapConfigPostgreSQL.xml");      
      SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
     

        }
    
    }


    public String getBancoDadosEscolhido() {
        return bancoDadosEscolhido;
    }

    public Reader getRd() {
        return rd;
    }

    public SqlMapClient getSmc() {
        return smc;
    }


}
