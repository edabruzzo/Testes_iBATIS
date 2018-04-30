/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author internet
 */
public class Program {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {

        Operacoes_Banco atividade = new Operacoes_Banco();

        atividade.criarTabela();
  
        atividade.inserir("Wellington", "Analista de Sistemas", 1, 6000f);
        atividade.inserir("Emmanuel", "Analista de Requisitos", 1, 5000f);
        atividade.inserir("Edwin", "Analista de Banco de Dados", 1, 7000f);
        atividade.inserir("Taiani", "Analista de Negócios", 1, 10000f);
        atividade.inserir("Antonio", "Gerente de Projetos", 1, 15000f);
        atividade.inserir("Pedro", "Analista de Suporte", 1, 2000f);
         
        atividade.listar();
        
        atividade.buscarID( 1);
        
        atividade.atualizar(1, 10000);
         
        atividade.remover(1);
    
        atividade.inserir("Wellington", "Analista de Sistemas", 1, 6000f);
        
        atividade.gerarFolhaPagamento();
        
        atividade.criaFuncaoTeste();

        atividade.chamandoFuncao("USUÁRIO INEXISTENTE NO BANCO !");
       

    }

}
